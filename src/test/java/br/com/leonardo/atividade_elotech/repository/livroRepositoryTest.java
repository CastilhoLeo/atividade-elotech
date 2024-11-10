package br.com.leonardo.atividade_elotech.repository;

import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.enums.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Array;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class livroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Test
    public void findByCategoriaIn_DeveRetornarUmaListaDeLivrosComBaseNaListaDeCategorias(){

        List<Categoria> categorias = List.of(Categoria.AVENTURA, Categoria.FICCAO);

        List<Livro> listaLivros = livroRepository.findByCategoriaIn(categorias);

        Assertions.assertNotNull(listaLivros);
        Assertions.assertEquals(List.of(1L, 2L, 3L, 4L, 8L, 9L, 10L), listaLivros.stream().map(e->e.getId()).toList());

    }

    @Test
    public void findByCategoriaIn_DeveRetornarListaVaziaAoReceberListaDeCategoriasVazia(){

        List<Categoria> categorias = List.of();

        List<Livro> listaLivros = livroRepository.findByCategoriaIn(categorias);

        Assertions.assertNotNull(listaLivros);
        Assertions.assertEquals(List.of(), listaLivros.stream().map(e->e.getId()).toList());

    }
}
