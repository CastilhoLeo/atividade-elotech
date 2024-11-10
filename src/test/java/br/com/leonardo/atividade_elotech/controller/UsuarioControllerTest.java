package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.dto.UsuarioDTO;
import br.com.leonardo.atividade_elotech.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void cadastrarUsuario () throws Exception{

        UsuarioDTO usuarioDTO = DTOBuilder.usuarioDTO();


        Mockito.when(usuarioService.cadastrarUsuario(any(UsuarioDTO.class))).thenReturn(usuarioDTO);


        mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Leonardo"))
                .andExpect(jsonPath("$.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.telefone").value("44998240563"));

    }

    @Test
    public void localizarPeloId() throws Exception{

        long usuarioId = 1L;
        UsuarioDTO usuarioDTO = DTOBuilder.usuarioDTO();


        Mockito.when(usuarioService.localizarPeloId(anyLong())).thenReturn(usuarioDTO);


        mockMvc.perform(get("/usuario/{id}", usuarioId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Leonardo"))
                .andExpect(jsonPath("$.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.telefone").value("44998240563"));
    }


    @Test
    public void deletarUsuario() throws Exception{

        long usuarioId = 1L;

        mockMvc.perform(delete("/usuario/{id}", usuarioId))
                .andExpect(status().isNoContent());

        Mockito.verify(usuarioService, Mockito.times(1)).deletarUsuario(1L);
    }


    @Test
    public void atualizarUsuario() throws Exception{

        long usuarioId = 1L;
        UsuarioDTO usuarioDTO = DTOBuilder.usuarioDTO();

        Mockito.when(usuarioService.atualizarUsuario(anyLong(), any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        mockMvc.perform(put("/usuario/{id}", usuarioId)
                        .content(objectMapper.writeValueAsString(usuarioDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Leonardo"))
                .andExpect(jsonPath("$.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.telefone").value("44998240563"));
    }

}
