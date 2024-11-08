package br.com.leonardo.atividade_elotech.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioDTO {

    private long id;

    private String nome;

    private String email;

    private LocalDate dataCadastro;

    private String telefone;

}
