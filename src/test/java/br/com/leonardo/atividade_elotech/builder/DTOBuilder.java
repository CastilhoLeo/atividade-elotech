package br.com.leonardo.atividade_elotech.builder;

import br.com.leonardo.atividade_elotech.dto.*;
import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.enums.Categoria;
import br.com.leonardo.atividade_elotech.enums.Status;

import java.time.LocalDate;

public class DTOBuilder {

    public static UsuarioDTO usuarioDTO(){

        return new UsuarioDTO(
                1L,
                "Leonardo",
                "leonardo@email.com",
                LocalDate.of(2024,11,9),
                "44998240563");
    }

    public static LivroDTO livroDTO(){

        return new LivroDTO(
                1L,
                "Livro teste",
                "Autor teste",
                "1234567891234",
                LocalDate.of(2020,10,10),
                Categoria.AVENTURA);
    }

    public static EmprestimoDTO emprestimoDTO(){

        return new EmprestimoDTO(
                1L,
                DTOBuilder.usuarioDTO(),
                DTOBuilder.livroDTO(),
                LocalDate.of(2024,11,9),
                LocalDate.of(2024,11,10));
    }

    public static RequestEmprestimoDTO requestEmprestimoDTO(){

        return new RequestEmprestimoDTO(
                1L,
                1L,
                new EmprestimoDTO(1L,
                        usuarioDTO(),
                        livroDTO(),
                        LocalDate.of(2024,11,9),
                        LocalDate.of(2024,11,10))
                );
    }

    public static RequestDevolucaoDTO requestDevolucaoDTO(){

        return new RequestDevolucaoDTO(
                Status.DEVOLVIDO);
    }
}
