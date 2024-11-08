package br.com.leonardo.atividade_elotech.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{

    public UsuarioNaoEncontradoException(){
        super("Usuário não encontrado!");
    }

    public UsuarioNaoEncontradoException(String message){
        super(message);
    }
}
