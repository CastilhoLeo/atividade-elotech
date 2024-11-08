package br.com.leonardo.atividade_elotech.dto;

import br.com.leonardo.atividade_elotech.enums.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
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
