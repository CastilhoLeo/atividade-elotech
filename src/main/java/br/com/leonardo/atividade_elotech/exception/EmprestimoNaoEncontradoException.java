package br.com.leonardo.atividade_elotech.exception;

public class EmprestimoNaoEncontradoException extends RuntimeException {

    public EmprestimoNaoEncontradoException(){
        super("Emprestimo não encontrado!");
    }

    public EmprestimoNaoEncontradoException(String message){
        super(message);
    }
}
