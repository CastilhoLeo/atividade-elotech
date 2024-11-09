package br.com.leonardo.atividade_elotech.dto;

import br.com.leonardo.atividade_elotech.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDevolucaoDTO {

    private Status status;

}
