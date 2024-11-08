package br.com.leonardo.atividade_elotech.enums;

public enum Status {

    EMPRESTADO(1, "Emprestado"),
    DISPONIVEL(2, "Disponivel");

    private String status;
    private int codStatus;

    Status(int codStatus , String status){
        this.codStatus = codStatus;
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public int getCodStatus(){
        return codStatus;
    }
}
