package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.dto.EmprestimoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestDevolucaoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestEmprestimoDTO;
import br.com.leonardo.atividade_elotech.service.EmprestimoService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class EmprestimoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmprestimoService emprestimoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void cadastrarEmprestimo () throws Exception{

        EmprestimoDTO emprestimoDTO = DTOBuilder.emprestimoDTO();
        RequestEmprestimoDTO requestEmprestimoDTO = DTOBuilder.requestEmprestimoDTO();

        Mockito.when(emprestimoService.cadastrarEmprestimo(any(RequestEmprestimoDTO.class))).thenReturn(emprestimoDTO);


        mockMvc.perform(post("/emprestimo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestEmprestimoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioDTO.id").value(1L))
                .andExpect(jsonPath("$.usuarioDTO.nome").value("Leonardo"))
                .andExpect(jsonPath("$.usuarioDTO.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.usuarioDTO.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.usuarioDTO.telefone").value("44998240563")).andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.livroDTO.titulo").value("Livro teste"))
                .andExpect(jsonPath("$.livroDTO.autor").value("Autor teste"))
                .andExpect(jsonPath("$.livroDTO.isbn").value("1234567891234"))
                .andExpect(jsonPath("$.livroDTO.dataPublicacao").value("2020-10-10"))
                .andExpect(jsonPath("$.livroDTO.categoria").value("AVENTURA"))
                .andExpect(jsonPath("$.dataEmprestimo").value("2024-11-09"))
                .andExpect(jsonPath("$.dataDevolucao").value("2024-11-10"));
    }


    @Test
    public void atualizarEmprestimo() throws Exception{

        long emprestimoId = 1L;
        EmprestimoDTO emprestimoDTO = DTOBuilder.emprestimoDTO();
        RequestDevolucaoDTO requestDevolucaoDTO = DTOBuilder.requestDevolucaoDTO();

        Mockito.when(emprestimoService.devolverEmprestimo(anyLong(), any(RequestDevolucaoDTO.class))).thenReturn(emprestimoDTO);

        mockMvc.perform(put("/emprestimo/{id}", emprestimoId)
                        .content(objectMapper.writeValueAsString(requestDevolucaoDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioDTO.id").value(1L))
                .andExpect(jsonPath("$.usuarioDTO.nome").value("Leonardo"))
                .andExpect(jsonPath("$.usuarioDTO.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.usuarioDTO.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.usuarioDTO.telefone").value("44998240563")).andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.livroDTO.titulo").value("Livro teste"))
                .andExpect(jsonPath("$.livroDTO.autor").value("Autor teste"))
                .andExpect(jsonPath("$.livroDTO.isbn").value("1234567891234"))
                .andExpect(jsonPath("$.livroDTO.dataPublicacao").value("2020-10-10"))
                .andExpect(jsonPath("$.livroDTO.categoria").value("AVENTURA"))
                .andExpect(jsonPath("$.dataEmprestimo").value("2024-11-09"))
                .andExpect(jsonPath("$.dataDevolucao").value("2024-11-10"));
    }

}
