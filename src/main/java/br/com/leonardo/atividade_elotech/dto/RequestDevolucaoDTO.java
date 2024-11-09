package br.com.leonardo.atividade_elotech.dto;

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

    private LocalDate dataDevolucao;

}
