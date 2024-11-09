package br.com.leonardo.atividade_elotech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "usuario")
@Check(constraints = "data_cadastro <= current_date")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    @Email(message = "E-mail inválido!")
    private String email;

    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dataCadastro;

    @Column(name = "telefone", nullable = false)
    @Size(min = 11, max = 11, message = "Numero de telefone inválido!")
    private String telefone;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Emprestimo> emprestimo = new ArrayList<>();

}
