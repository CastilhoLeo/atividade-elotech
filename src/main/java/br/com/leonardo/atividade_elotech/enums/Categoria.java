package br.com.leonardo.atividade_elotech.enums;

public enum Categoria {

    COMEDIA(1, "Masculino"),
    ROMANCE(2, "Romance"),
    DRAMA(3, "Drama");

    private String categoria;
    private int codCategoria;

    Categoria(int codCategoria, String categoria){
        this.categoria = categoria;
        this.codCategoria = codCategoria;
    }

    public String getCategoria(){
        return categoria;
    }

    public int getCodCategoria(){
        return codCategoria;
    }

}
