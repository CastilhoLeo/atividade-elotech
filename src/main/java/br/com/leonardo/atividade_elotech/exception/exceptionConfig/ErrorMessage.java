package br.com.leonardo.atividade_elotech.exception.exceptionConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private HttpStatus httpStatus;

    private String message;
}
