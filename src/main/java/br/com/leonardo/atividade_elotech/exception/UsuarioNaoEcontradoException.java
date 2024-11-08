package br.com.leonardo.atividade_elotech.exception;

public class UsuarioNaoEcontradoException extends RuntimeException{

    public UsuarioNaoEcontradoException(){
        super("Usuário não encontrado!");
    }

    public UsuarioNaoEcontradoException(String message){
        super(message);
    }
}
