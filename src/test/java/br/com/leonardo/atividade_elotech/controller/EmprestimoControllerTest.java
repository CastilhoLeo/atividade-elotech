package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.builder.EntityBuilder;
import br.com.leonardo.atividade_elotech.dto.EmprestimoDTO;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.dto.RequestDevolucaoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestEmprestimoDTO;
import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.service.EmprestimoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
    public void pesquisarTodos() throws Exception{

        Pageable pageable = PageRequest.of(1, 20);

        EmprestimoDTO emprestimoDTO1 = DTOBuilder.emprestimoDTO();
        EmprestimoDTO emprestimoDTO2 = DTOBuilder.emprestimoDTO();
        emprestimoDTO2.setId(2L);
        Page<EmprestimoDTO> emprestimos = new PageImpl<>(List.of(emprestimoDTO1, emprestimoDTO2));


        Mockito.when(emprestimoService.pesquisaDinamicaEmprestimo(any(String.class), any(String.class), any(Pageable.class))).thenReturn(emprestimos);


        mockMvc.perform(get("/emprestimo")
                        .param("cliente", "teste")
                        .param("titulo", "")
                        .param("page", "1")
                        .param("size", "20"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].clienteDTO.id").value(1L))
                .andExpect(jsonPath("$.content[0].clienteDTO.nome").value("Leonardo"))
                .andExpect(jsonPath("$.content[0].clienteDTO.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.content[0].clienteDTO.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.content[0].clienteDTO.telefone").value("44998240563"))
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].livroDTO.titulo").value("Livro teste"))
                .andExpect(jsonPath("$.content[0].livroDTO.autor").value("Autor teste"))
                .andExpect(jsonPath("$.content[0].livroDTO.isbn").value("1234567891234"))
                .andExpect(jsonPath("$.content[0].livroDTO.dataPublicacao").value("2020-10-10"))
                .andExpect(jsonPath("$.content[0].livroDTO.categoria").value("AVENTURA"))
                .andExpect(jsonPath("$.content[0].dataEmprestimo").value("2024-11-09"))
                .andExpect(jsonPath("$.content[0].dataDevolucao").value("2024-11-10"))
                .andExpect(jsonPath("$.content[1].id").value(2L))
                .andExpect(jsonPath("$.content[1].clienteDTO.id").value(1L))
                .andExpect(jsonPath("$.content[1].clienteDTO.nome").value("Leonardo"))
                .andExpect(jsonPath("$.content[1].clienteDTO.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.content[1].clienteDTO.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.content[1].clienteDTO.telefone").value("44998240563"))
                .andExpect(jsonPath("$.content[1].id").value(2L))
                .andExpect(jsonPath("$.content[1].livroDTO.titulo").value("Livro teste"))
                .andExpect(jsonPath("$.content[1].livroDTO.autor").value("Autor teste"))
                .andExpect(jsonPath("$.content[1].livroDTO.isbn").value("1234567891234"))
                .andExpect(jsonPath("$.content[1].livroDTO.dataPublicacao").value("2020-10-10"))
                .andExpect(jsonPath("$.content[1].livroDTO.categoria").value("AVENTURA"))
                .andExpect(jsonPath("$.content[1].dataEmprestimo").value("2024-11-09"))
                .andExpect(jsonPath("$.content[1].dataDevolucao").value("2024-11-10"));

    }


    @Test
    public void cadastrarEmprestimo () throws Exception{

        EmprestimoDTO emprestimoDTO = DTOBuilder.emprestimoDTO();
        RequestEmprestimoDTO requestEmprestimoDTO = DTOBuilder.requestEmprestimoDTO();

        Mockito.when(emprestimoService.cadastrarEmprestimo(any(RequestEmprestimoDTO.class))).thenReturn(emprestimoDTO);


        mockMvc.perform(post("/emprestimo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestEmprestimoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteDTO.id").value(1L))
                .andExpect(jsonPath("$.clienteDTO.nome").value("Leonardo"))
                .andExpect(jsonPath("$.clienteDTO.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.clienteDTO.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.clienteDTO.telefone").value("44998240563")).andExpect(jsonPath("$.id").value(1L))
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
        LocalDate dataDevolucao = LocalDate.of(2024,11,11);

        Mockito.when(emprestimoService.devolverEmprestimo(1L, dataDevolucao)).thenReturn(emprestimoDTO);

        mockMvc.perform(put("/emprestimo/{id}", emprestimoId)
                        .content(objectMapper.writeValueAsString(dataDevolucao))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteDTO.id").value(1L))
                .andExpect(jsonPath("$.clienteDTO.nome").value("Leonardo"))
                .andExpect(jsonPath("$.clienteDTO.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.clienteDTO.dataCadastro").value("2024-11-09"))
                .andExpect(jsonPath("$.clienteDTO.telefone").value("44998240563"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.livroDTO.titulo").value("Livro teste"))
                .andExpect(jsonPath("$.livroDTO.autor").value("Autor teste"))
                .andExpect(jsonPath("$.livroDTO.isbn").value("1234567891234"))
                .andExpect(jsonPath("$.livroDTO.dataPublicacao").value("2020-10-10"))
                .andExpect(jsonPath("$.livroDTO.categoria").value("AVENTURA"))
                .andExpect(jsonPath("$.dataEmprestimo").value("2024-11-09"))
                .andExpect(jsonPath("$.dataDevolucao").value("2024-11-10"))
                .andExpect(jsonPath("$.status").value("EMPRESTADO"));


    }

}
