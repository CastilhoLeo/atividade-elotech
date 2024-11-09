package br.com.leonardo.atividade_elotech.enums;

public enum Categoria {

    AVENTURA(1, "Aventura"),
    ROMANCE(2, "Romance"),
    FICCAO(3, "Ficção");

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
