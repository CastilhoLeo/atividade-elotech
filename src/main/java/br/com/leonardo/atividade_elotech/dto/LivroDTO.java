package br.com.leonardo.atividade_elotech.dto;

import br.com.leonardo.atividade_elotech.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class LivroDTO {

    private long id;

    private String titulo;

    private String autor;

    private String isbn;

    private LocalDate dataPublicacao;

    private Categoria categoria;

}
