package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.dto.ClienteDTO;
import br.com.leonardo.atividade_elotech.service.ClienteService;
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
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void cadastrarcliente () throws Exception{

        ClienteDTO clienteDTO = DTOBuilder.clienteDTO();


        Mockito.when(clienteService.cadastrarcliente(any(ClienteDTO.class))).thenReturn(clienteDTO);


        mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Leonardo"))
                .andExpect(jsonPath("$.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.telefone").value("44998240563"));

    }

    @Test
    public void localizarPeloId() throws Exception{

        long clienteId = 1L;
        ClienteDTO clienteDTO = DTOBuilder.clienteDTO();


        Mockito.when(clienteService.localizarPeloId(anyLong())).thenReturn(clienteDTO);


        mockMvc.perform(get("/cliente/{id}", clienteId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Leonardo"))
                .andExpect(jsonPath("$.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.telefone").value("44998240563"));
    }


    @Test
    public void deletarcliente() throws Exception{

        long clienteId = 1L;

        mockMvc.perform(delete("/cliente/{id}", clienteId))
                .andExpect(status().isNoContent());

        Mockito.verify(clienteService, Mockito.times(1)).deletarcliente(1L);
    }


    @Test
    public void atualizarcliente() throws Exception{

        long clienteId = 1L;
        ClienteDTO clienteDTO = DTOBuilder.clienteDTO();

        Mockito.when(clienteService.atualizarcliente(anyLong(), any(ClienteDTO.class))).thenReturn(clienteDTO);

        mockMvc.perform(put("/cliente/{id}", clienteId)
                        .content(objectMapper.writeValueAsString(clienteDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Leonardo"))
                .andExpect(jsonPath("$.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.telefone").value("44998240563"));
    }

}
