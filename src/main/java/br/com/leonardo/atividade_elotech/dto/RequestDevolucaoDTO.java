package br.com.leonardo.atividade_elotech.dto;

import br.com.leonardo.atividade_elotech.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Classe que recebe os dados necessários para realizar a devolução de empréstimo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDevolucaoDTO {

    private Status status;

    private LocalDate dataDevolucao;

}
