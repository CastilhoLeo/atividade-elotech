package br.com.leonardo.atividade_elotech.enums;

/**
 * Categorias dos livros
 */
public enum Categoria {

    AVENTURA("Aventura"),
    ROMANCE("Romance"),
    FICCAO("Ficção");

    private String categoria;

    Categoria(String categoria){
        this.categoria = categoria;
    }

    public String getCategoria(){
        return categoria;
    }


}
