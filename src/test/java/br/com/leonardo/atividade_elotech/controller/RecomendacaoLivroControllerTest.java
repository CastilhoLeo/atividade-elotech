package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.service.RecomendacaoLivroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class RecomendacaoLivroControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecomendacaoLivroService recomendacaoLivroService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void recomendacaoLivro() throws Exception {

        long livroId = 1L;
        LivroDTO livroDTO1 = DTOBuilder.livroDTO();
        LivroDTO livroDTO2 = DTOBuilder.livroDTO();
        livroDTO2.setId(2L);
        List<LivroDTO> recomendacoes = List.of(livroDTO1, livroDTO2);


        Mockito.when(recomendacaoLivroService.recomendacoesDoUsuario(anyLong())).thenReturn(recomendacoes);


        mockMvc.perform(get("/recomendacao/{id}", livroId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].titulo").value("Livro teste"))
                .andExpect(jsonPath("$.[0].autor").value("Autor teste"))
                .andExpect(jsonPath("$.[0].isbn").value("1234567891234"))
                .andExpect(jsonPath("$.[0].dataPublicacao").value("2020-10-10"))
                .andExpect(jsonPath("$.[0].categoria").value("AVENTURA"))
                .andExpect(jsonPath("$.[1].id").value(2L))
                .andExpect(jsonPath("$.[1].titulo").value("Livro teste"))
                .andExpect(jsonPath("$.[1].autor").value("Autor teste"))
                .andExpect(jsonPath("$.[1].isbn").value("1234567891234"))
                .andExpect(jsonPath("$.[1].dataPublicacao").value("2020-10-10"))
                .andExpect(jsonPath("$.[1].categoria").value("AVENTURA"));
    }
}
