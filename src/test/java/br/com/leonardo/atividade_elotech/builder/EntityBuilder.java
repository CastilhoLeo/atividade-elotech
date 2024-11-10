package br.com.leonardo.atividade_elotech.builder;

import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.entity.Usuario;
import br.com.leonardo.atividade_elotech.enums.Categoria;
import br.com.leonardo.atividade_elotech.enums.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntityBuilder {

    public static Usuario usuario(){

        return new Usuario(
                1L,
                "Leonardo",
                "Leonardo@email.com",
                LocalDate.of(2024,11,9),
                "44998240563",
                List.of(new Emprestimo()));
    }

    public static Livro livro(){

        return new Livro(
                1L,
                "Livro teste",
                "Autor teste",
                "1234567891234",
                LocalDate.of(2020,10,10),
                Categoria.AVENTURA,
                new ArrayList<Emprestimo>());
    }




    public static Emprestimo emprestimo(){
        return new Emprestimo(
                1L,
                EntityBuilder.usuario(),
                EntityBuilder.livro(),
                LocalDate.of(2024,11,9),
                null,
                Status.DEVOLVIDO);
    }

}
