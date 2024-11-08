package br.com.leonardo.atividade_elotech.exception.exceptionConfig;

import br.com.leonardo.atividade_elotech.exception.UsuarioNaoEcontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsuarioNaoEcontradoException.class)
    public ResponseEntity<ErrorMessage> usuarioNaoEncontrado(UsuarioNaoEcontradoException usuarioNaoEcontradoException){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, usuarioNaoEcontradoException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
