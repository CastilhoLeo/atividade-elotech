package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.service.LivroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService livroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void cadastrarLivro () throws Exception{

        LivroDTO livroDTO = DTOBuilder.livroDTO();


        Mockito.when(livroService.cadastrarLivro(any(LivroDTO.class))).thenReturn(livroDTO);


        mockMvc.perform(post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livroDTO)))
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Livro teste"))
                .andExpect(jsonPath("$.autor").value("Autor teste"))
                .andExpect(jsonPath("$.isbn").value("1234567891234"))
                .andExpect(jsonPath("$.dataPublicacao").value("2020-10-10"))
                .andExpect(jsonPath("$.categoria").value("AVENTURA"));



    }

    @Test
    public void localizarPeloId() throws Exception{

        long livroId = 1L;
        LivroDTO livroDTO = DTOBuilder.livroDTO();


        Mockito.when(livroService.localizarPeloId(anyLong())).thenReturn(livroDTO);


        mockMvc.perform(get("/livro/{id}", livroId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Livro teste"))
                .andExpect(jsonPath("$.autor").value("Autor teste"))
                .andExpect(jsonPath("$.isbn").value("1234567891234"))
                .andExpect(jsonPath("$.dataPublicacao").value("2020-10-10"))
                .andExpect(jsonPath("$.categoria").value("AVENTURA"));

    }

    @Test
    public void deletarLivro() throws Exception{

        long livroId = 1L;

        mockMvc.perform(delete("/livro/{id}", livroId))
                .andExpect(status().isNoContent());

        Mockito.verify(livroService, Mockito.times(1)).deletarLivro(1L);
    }

    @Test
    public void atualizarLivro() throws Exception{

        long livroId = 1L;
        LivroDTO livroDTO = DTOBuilder.livroDTO();

        Mockito.when(livroService.atualizarLivro(anyLong(), any(LivroDTO.class))).thenReturn(livroDTO);

        mockMvc.perform(put("/livro/{id}", livroId)
                .content(objectMapper.writeValueAsString(livroDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Livro teste"))
                .andExpect(jsonPath("$.autor").value("Autor teste"))
                .andExpect(jsonPath("$.isbn").value("1234567891234"))
                .andExpect(jsonPath("$.dataPublicacao").value("2020-10-10"))
                .andExpect(jsonPath("$.categoria").value("AVENTURA"));

    }


}
