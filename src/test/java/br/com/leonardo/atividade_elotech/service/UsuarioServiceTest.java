package br.com.leonardo.atividade_elotech.service;


import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.builder.EntityBuilder;
import br.com.leonardo.atividade_elotech.converter.UsuarioConverter;
import br.com.leonardo.atividade_elotech.dto.UsuarioDTO;
import br.com.leonardo.atividade_elotech.entity.Usuario;
import br.com.leonardo.atividade_elotech.exception.UsuarioNaoEncontradoException;
import br.com.leonardo.atividade_elotech.repository.UsuarioRepository;
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
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioConverter usuarioConverter;

    @InjectMocks
    private UsuarioService usuarioService;


    @Test
    public void cadastrarUsuario_DeveRetornarUmUsuarioSalvo() {

        UsuarioDTO usuarioDTO = DTOBuilder.usuarioDTO();
        Usuario usuario = EntityBuilder.usuario();

        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Mockito.when(usuarioConverter.toDto(usuario)).thenReturn(usuarioDTO);
        Mockito.when(usuarioConverter.toEntity(any(UsuarioDTO.class))).thenReturn(usuario);

        UsuarioDTO usuarioSalvo = usuarioService.cadastrarUsuario(usuarioDTO);

        Assertions.assertNotNull(usuarioSalvo);
        Assertions.assertEquals(usuarioSalvo.getClass(), UsuarioDTO.class);
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(usuario);
        Mockito.verify(usuarioConverter, Mockito.times(1)).toDto(usuario);
        Mockito.verify(usuarioConverter, Mockito.times(1)).toEntity(usuarioDTO);

    }

    @Test
    public void localizarPeloId_DeveRetornarOUsuarioBuscadoPeloId() {

        long usuarioId = 1L;
        UsuarioDTO usuarioDTO = DTOBuilder.usuarioDTO();
        Usuario usuario = EntityBuilder.usuario();

        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioConverter.toDto(any(Usuario.class))).thenReturn(usuarioDTO);

        UsuarioDTO usuarioLocalizado = usuarioService.localizarPeloId(usuarioId);

        Assertions.assertNotNull(usuarioLocalizado);
        Assertions.assertEquals(usuarioLocalizado.getClass(), UsuarioDTO.class);
        Mockito.verify(usuarioRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(usuarioConverter, Mockito.times(1)).toDto(usuario);

    }

    @Test
    public void localizarPeloId_DeveRetornarUsuarioNaoEncontradoException() {

        long usuarioId = 19L;

        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, ()->usuarioService.localizarPeloId(usuarioId));
        Mockito.verify(usuarioRepository, Mockito.times(1)).findById(19L);

    }

    @Test
    public void deletarUsuario_DeveDeletarUmUsuarioPeloId() {

        long usuarioId = 1L;

        usuarioService.deletarUsuario(usuarioId);

        Mockito.verify(usuarioRepository, Mockito.times(1)).deleteById(1L);

    }

    @Test
    public void atualizarUsuario_DeveAtualizarOsDadosDoUsuario(){

        long usuarioId = 1L;
        Usuario usuario = EntityBuilder.usuario();
        UsuarioDTO usuarioDTO = DTOBuilder.usuarioDTO();
                usuarioDTO.setNome("Nome atualizado");
                usuarioDTO.setEmail("atualizado@email.com");
                usuarioDTO.setDataCadastro(LocalDate.of(2000, 10,10));
                usuarioDTO.setTelefone("4499999999");


        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioConverter.toDto(any(Usuario.class))).thenReturn(usuarioDTO);

        usuarioService.atualizarUsuario(usuarioId, usuarioDTO);

        Mockito.verify(usuarioRepository, Mockito.times(1)).save(usuario);
        Assertions.assertEquals(usuario.getNome(), usuarioDTO.getNome());
        Assertions.assertEquals(usuario.getTelefone(), usuarioDTO.getTelefone());
        Assertions.assertEquals(usuario.getEmail(), usuarioDTO.getEmail());
        Assertions.assertEquals(usuario.getDataCadastro(), usuarioDTO.getDataCadastro());

    }

    @Test
    public void atualizarUsuario_DeveRetornarUsuarioNaoEncontradoException() {

        long usuarioId = 19L;

        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.localizarPeloId(usuarioId));
        Mockito.verify(usuarioRepository, Mockito.times(1)).findById(19L);
    }

}
