package br.com.leonardo.atividade_elotech.exception.exceptionConfig;

import br.com.leonardo.atividade_elotech.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> usuarioNaoEncontrado(UsuarioNaoEncontradoException usuarioNaoEcontradoException){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, usuarioNaoEcontradoException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> usuarioNaoEncontrado(LivroNaoEncontradoException livroNaoEcontradoException){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, livroNaoEcontradoException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(EmprestimoNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> emprestimoNaoEncontrado (EmprestimoNaoEncontradoException emprestimoNaoEncontradoException){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, emprestimoNaoEncontradoException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(DataDeDevolucaoException.class)
    public ResponseEntity<ErrorMessage> DataDeDevolucaoException (DataDeDevolucaoException dataDeDevolucaoException){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, dataDeDevolucaoException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(LivroIndisponivelException.class)
    public ResponseEntity<ErrorMessage> livroIndisponivelException (LivroIndisponivelException livroIndisponivelException){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, livroIndisponivelException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> DataIntegrityViolation (DataIntegrityViolationException dataIntegrityViolationException){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, "Dados inválidos ou incompletos!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}
