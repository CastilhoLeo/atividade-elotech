package br.com.leonardo.atividade_elotech.entity;

import br.com.leonardo.atividade_elotech.enums.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "data_publicacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dataPublicacao;

    @Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Categoria categoria;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Emprestimo> emprestimo = new ArrayList<>();
}
