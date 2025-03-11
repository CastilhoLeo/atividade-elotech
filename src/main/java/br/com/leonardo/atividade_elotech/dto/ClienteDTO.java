package br.com.leonardo.atividade_elotech.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ClienteDTO {

    private long id;

    private String nome;

    private String email;

    private LocalDate dataCadastro;

    private String telefone;

}
