package br.com.leonardo.atividade_elotech.builder;

import br.com.leonardo.atividade_elotech.dto.*;
import br.com.leonardo.atividade_elotech.enums.Categoria;
import br.com.leonardo.atividade_elotech.enums.Status;

import java.time.LocalDate;

/**
 * Classe criada com métodos static para criação dos objetos DTO utilizados nos testes unitários
 */
public class DTOBuilder {

    public static ClienteDTO clienteDTO(){

        return new ClienteDTO(
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
                DTOBuilder.clienteDTO(),
                DTOBuilder.livroDTO(),
                LocalDate.of(2024,11,9),
                LocalDate.of(2024,11,10),
                Status.EMPRESTADO);
    }

    public static RequestEmprestimoDTO requestEmprestimoDTO(){

        return new RequestEmprestimoDTO(
                1L,
                1L,
                LocalDate.of(2024,11,9)
                );
    }

    public static RequestDevolucaoDTO requestDevolucaoDTO(){
        return new RequestDevolucaoDTO(
                LocalDate.of(2024,11,11));
    }
}
