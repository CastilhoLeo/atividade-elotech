package br.com.leonardo.atividade_elotech.repository;

import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class EmprestimoRepositoryTest {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Test
    public void findByUsuarioId_DeveRetornarTodosEmprestimosDoUsuarioBuscadoPeloId(){

        long usuarioId = 1L;

        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioId(usuarioId);

        Assertions.assertNotNull(emprestimos);
        Assertions.assertEquals(List.of(1L,2L,3L), emprestimos.stream().map(e->e.getId()).toList());

    }

    @Test
    public void findByUsuarioId_DeveRetornarListVazioDoUsuarioQueNaoTemEmprestimo(){

        long usuarioId = 4L;

        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioId(usuarioId);

        Assertions.assertNotNull(emprestimos);
        Assertions.assertEquals(List.of(), emprestimos.stream().map(e->e.getId()).toList());

    }


    @Test
    public void findByLivroId_DeveRetornarTodosEmprestimosDoLivroBuscadoPeloId(){

        long livroId = 1L;

        List<Emprestimo> emprestimos = emprestimoRepository.findByLivroId(livroId);

        Assertions.assertNotNull(emprestimos);
        Assertions.assertEquals(List.of(1L), emprestimos.stream().map(e->e.getId()).toList());

    }


    @Test
    public void findByLivroId_DeveRetornarListVazioDoULivroQueNaoTemEmprestimo(){

        long livroId = 5L;

        List<Emprestimo> emprestimos = emprestimoRepository.findByLivroId(livroId);

        Assertions.assertNotNull(emprestimos);
        Assertions.assertEquals(List.of(), emprestimos.stream().map(e->e.getId()).toList());

    }

}
