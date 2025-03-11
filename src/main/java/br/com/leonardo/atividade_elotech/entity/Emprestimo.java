package br.com.leonardo.atividade_elotech.entity;

import br.com.leonardo.atividade_elotech.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "emprestimo")
@Check(constraints = "data_emprestimo <= current_date")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Column(name = "data_emprestimo", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao") // Poderá ser null devido a não ter data de devolução no momento do cadastro
    @Temporal(TemporalType.DATE)
    private LocalDate dataDevolucao;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

}
