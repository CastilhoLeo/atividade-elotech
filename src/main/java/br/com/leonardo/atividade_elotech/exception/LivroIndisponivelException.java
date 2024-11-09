package br.com.leonardo.atividade_elotech.exception;

public class LivroIndisponivelException extends RuntimeException{

    public LivroIndisponivelException(){
        super("Este livro já está emprestado! Aguarde a devolução do mesmo.");
    }

    public LivroIndisponivelException(String message){
        super(message);
    }
}
