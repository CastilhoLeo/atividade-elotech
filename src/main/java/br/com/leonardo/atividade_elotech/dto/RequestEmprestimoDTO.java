package br.com.leonardo.atividade_elotech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * Classe que recebe os dados necessários para realizar um empréstimo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEmprestimoDTO {

    private long livroId;

    private long clienteId;

    private LocalDate dataEmprestimo;
}
