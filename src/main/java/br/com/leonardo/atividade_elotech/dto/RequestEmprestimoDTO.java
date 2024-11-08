package br.com.leonardo.atividade_elotech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEmprestimoDTO {

    private long livroId;

    private long usuarioId;

    private EmprestimoDTO EmprestimoDTO;
}
