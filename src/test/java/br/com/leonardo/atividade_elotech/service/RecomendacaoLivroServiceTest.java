package br.com.leonardo.atividade_elotech.service;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.builder.EntityBuilder;
import br.com.leonardo.atividade_elotech.converter.LivroConverter;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.repository.EmprestimoRepository;
import br.com.leonardo.atividade_elotech.repository.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class RecomendacaoLivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private LivroConverter livroConverter;

    @InjectMocks
    private RecomendacaoLivroService recomendacaoLivroService;


    @Test
    public void recomendacoesDoUsuario_DeveRetornarUmListComOsLivrosDeCategoriaAventura(){

        long usuarioId = 1l;

        //Emprestimos já feitos pelo cliente -> Livro de id 1 e categoria AVENTURA
        List<Emprestimo> emprestimos = new ArrayList<>(List.of(EntityBuilder.emprestimo()));

        //Todos os Livros da biblioteca com categoria = AVENTURA
        Livro livro1 = EntityBuilder.livro();
        Livro livro2 = EntityBuilder.livro();
        livro2.setId(2L);
        Livro livro3 = EntityBuilder.livro();
        livro3.setId(3L);
        List<Livro> livrosDaCategoria = new ArrayList<>(List.of(livro1, livro2, livro3)); // Lista com os livros retornados

        //Livros que serão retornados na recomendação (livro 1 será excluído da relação por já ter sido emprestado)
        LivroDTO livroDto2 = DTOBuilder.livroDTO();
        livroDto2.setId(2L);
        LivroDTO livroDto3 = DTOBuilder.livroDTO();
        livroDto3.setId(3L);


        Mockito.when(emprestimoRepository.findByUsuarioId(anyLong())).thenReturn(emprestimos);
        Mockito.when(livroRepository.findByCategoriaIn(anyList())).thenReturn(livrosDaCategoria);
        Mockito.when(livroConverter.toDto(livro2)).thenReturn(livroDto2);
        Mockito.when(livroConverter.toDto(livro3)).thenReturn(livroDto3);

        List<LivroDTO> resultado = recomendacaoLivroService.recomendacoesDoUsuario(usuarioId);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(resultado, List.of(livroDto2, livroDto3));

    }
}
