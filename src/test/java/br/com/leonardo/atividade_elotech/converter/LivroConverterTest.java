package br.com.leonardo.atividade_elotech.converter;


import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.builder.EntityBuilder;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.entity.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class LivroConverterTest {

    @Autowired
    private LivroConverter livroConverter;

    @Test
    public void deveConverterLivroEntityEmLivroDTO(){

        Livro livro = EntityBuilder.livro();

        LivroDTO livroDTO = livroConverter.toDto(livro);

        Assertions.assertNotNull(livroDTO);
        Assertions.assertEquals(LivroDTO.class, livroDTO.getClass());
        Assertions.assertEquals(livro.getId(), livroDTO.getId());
        Assertions.assertEquals(livro.getTitulo(), livroDTO.getTitulo());
        Assertions.assertEquals(livro.getAutor(), livroDTO.getAutor());
        Assertions.assertEquals(livro.getDataPublicacao(), livroDTO.getDataPublicacao());
        Assertions.assertEquals(livro.getCategoria(), livroDTO.getCategoria());

    }

    @Test
    public void deveConverterLivroDTOEmLivroEntity(){

        LivroDTO livroDTO = DTOBuilder.livroDTO();

        Livro livro = livroConverter.toEntity(livroDTO);

        Assertions.assertNotNull(livro);
        Assertions.assertEquals(Livro.class, livro.getClass());
        Assertions.assertEquals(livro.getId(), livroDTO.getId());
        Assertions.assertEquals(livro.getTitulo(), livroDTO.getTitulo());
        Assertions.assertEquals(livro.getAutor(), livroDTO.getAutor());
        Assertions.assertEquals(livro.getDataPublicacao(), livroDTO.getDataPublicacao());
        Assertions.assertEquals(livro.getCategoria(), livroDTO.getCategoria());

    }
}
