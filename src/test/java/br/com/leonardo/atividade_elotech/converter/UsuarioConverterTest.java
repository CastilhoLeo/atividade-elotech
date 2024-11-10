package br.com.leonardo.atividade_elotech.converter;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.builder.EntityBuilder;
import br.com.leonardo.atividade_elotech.dto.UsuarioDTO;
import br.com.leonardo.atividade_elotech.entity.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class UsuarioConverterTest {


    @Autowired
    private UsuarioConverter usuarioConverter;


    @Test
    public void toDto_deveConverterUsuarioEntityEmUsuarioDTO(){

        Usuario usuario = EntityBuilder.usuario();

        UsuarioDTO usuarioDTO = usuarioConverter.toDto(usuario);

        Assertions.assertNotNull(usuarioDTO);
        Assertions.assertEquals(UsuarioDTO.class, usuarioDTO.getClass());
        Assertions.assertEquals(usuarioDTO.getId(), usuario.getId());
        Assertions.assertEquals(usuarioDTO.getNome(), usuario.getNome());
        Assertions.assertEquals(usuarioDTO.getTelefone(), usuario.getTelefone());
        Assertions.assertEquals(usuarioDTO.getEmail(), usuario.getEmail());
        Assertions.assertEquals(usuarioDTO.getDataCadastro(), usuario.getDataCadastro());

    }

    @Test
    public void toEntity_deveConverterUmUsuarioDTOEmUsuarioEntity(){

        UsuarioDTO usuarioDTO = DTOBuilder.usuarioDTO();

        Usuario usuario = usuarioConverter.toEntity(usuarioDTO);

        Assertions.assertNotNull(usuario);
        Assertions.assertEquals(Usuario.class, usuario.getClass());
        Assertions.assertEquals(usuario.getId(), usuarioDTO.getId());
        Assertions.assertEquals(usuario.getNome(), usuarioDTO.getNome());
        Assertions.assertEquals(usuario.getTelefone(), usuarioDTO.getTelefone());
        Assertions.assertEquals(usuario.getEmail(), usuarioDTO.getEmail());
        Assertions.assertEquals(usuario.getDataCadastro(), usuarioDTO.getDataCadastro());

    }
}
