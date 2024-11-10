package br.com.leonardo.atividade_elotech.service;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.builder.EntityBuilder;
import br.com.leonardo.atividade_elotech.converter.LivroConverter;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.enums.Categoria;
import br.com.leonardo.atividade_elotech.enums.Status;
import br.com.leonardo.atividade_elotech.exception.LivroNaoEncontradoException;
import br.com.leonardo.atividade_elotech.repository.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private LivroConverter livroConverter;

    @InjectMocks
    private LivroService livroService;

    @Test
    public void cadastrarLivro_DeveRetornarUmLivroSalvo(){

        LivroDTO livroDTO = DTOBuilder.livroDTO();
        Livro livro = EntityBuilder.livro();

        Mockito.when(livroRepository.save(livro)).thenReturn(livro);
        Mockito.when(livroConverter.toDto(livro)).thenReturn(livroDTO);
        Mockito.when(livroConverter.toEntity(any(LivroDTO.class))).thenReturn(livro);

        LivroDTO livroSalvo = livroService.cadastrarLivro(livroDTO);

        Assertions.assertNotNull(livroSalvo);
        Assertions.assertEquals(livroSalvo.getClass(), LivroDTO.class);
        Mockito.verify(livroRepository, Mockito.times(1)).save(livro);
        Mockito.verify(livroConverter, Mockito.times(1)).toDto(livro);
        Mockito.verify(livroConverter, Mockito.times(1)).toEntity(livroDTO);
    }

    @Test
    public void localizarPeloId_DeveRetornarOLivroBuscadoPeloId() {

        long livroId = 1L;
        LivroDTO livroDTO = DTOBuilder.livroDTO();
        Livro livro = EntityBuilder.livro();

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        Mockito.when(livroConverter.toDto(any(Livro.class))).thenReturn(livroDTO);

        LivroDTO livroLocalizado = livroService.localizarPeloId(livroId);

        Assertions.assertNotNull(livroLocalizado);
        Assertions.assertEquals(livroLocalizado.getClass(), LivroDTO.class);
        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(livroConverter, Mockito.times(1)).toDto(livro);

    }

    @Test
    public void localizarPeloId_DeveRetornarLivroNaoEncontradoException() {

        long livroId = 19L;

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(LivroNaoEncontradoException.class, ()->livroService.localizarPeloId(livroId));
        Mockito.verify(livroRepository, Mockito.times(1)).findById(19L);

    }

    @Test
    public void deletarLivro_DeveDeletarUmLivroPeloId() {

        long livroId = 1L;

        livroService.deletarLivro(livroId);

        Mockito.verify(livroRepository, Mockito.times(1)).deleteById(1L);

    }

    @Test
    public void atualizarLivro_DeveAtualizarOsDadosDoLivro(){

        long livroId = 1L;
        Livro livro = EntityBuilder.livro();
        LivroDTO livroDTO = DTOBuilder.livroDTO();
        livroDTO.setTitulo("Titulo atualizado");
        livroDTO.setIsbn("000000000000");
        livroDTO.setDataPublicacao(LocalDate.of(2000, 10,10));
        livroDTO.setAutor("Autor atualizado");
        livroDTO.setCategoria(Categoria.ROMANCE);


        Mockito.when(livroRepository.save(any(Livro.class))).thenReturn(livro);
        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        Mockito.when(livroConverter.toDto(any(Livro.class))).thenReturn(livroDTO);

        livroService.atualizarLivro(livroId, livroDTO);

        Mockito.verify(livroRepository, Mockito.times(1)).save(livro);
        Assertions.assertEquals(livro.getAutor(), livroDTO.getAutor());
        Assertions.assertEquals(livro.getTitulo(), livroDTO.getTitulo());
        Assertions.assertEquals(livro.getIsbn(), livroDTO.getIsbn());
        Assertions.assertEquals(livro.getDataPublicacao(), livroDTO.getDataPublicacao());
        Assertions.assertEquals(livro.getCategoria(), livroDTO.getCategoria());

    }

    @Test
    public void atualizarLivro_DeveRetornarLivroNaoEncontradoException() {

        long livroId = 19L;

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(LivroNaoEncontradoException.class, () -> livroService.localizarPeloId(livroId));
        Mockito.verify(livroRepository, Mockito.times(1)).findById(19L);
    }
}
