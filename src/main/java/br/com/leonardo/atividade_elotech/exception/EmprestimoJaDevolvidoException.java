package br.com.leonardo.atividade_elotech.exception;

public class EmprestimoJaDevolvidoException  extends RuntimeException{

    public EmprestimoJaDevolvidoException(){
        super("Este empréstimo já foi devolvido!");
    }

    public EmprestimoJaDevolvidoException(String message){
        super(message);
    }
}
