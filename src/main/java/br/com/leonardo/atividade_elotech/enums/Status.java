package br.com.leonardo.atividade_elotech.enums;

public enum Status {

    EMPRESTADO("Emprestado"),
    DEVOLVIDO("Devolvido");

    private String status;

    Status(String status){

        this.status = status;
    }

    public String getStatus(){return status;}

}
