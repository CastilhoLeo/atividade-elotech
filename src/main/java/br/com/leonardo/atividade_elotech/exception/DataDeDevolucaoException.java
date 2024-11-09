package br.com.leonardo.atividade_elotech.exception;

public class DataDeDevolucaoException extends RuntimeException{

    public DataDeDevolucaoException(){
        super("A data de devolução não pode ser inferior à data de emprestimo!");
    }

    public DataDeDevolucaoException(String message){
        super(message);
    }
}
