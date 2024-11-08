package br.com.leonardo.atividade_elotech.dto;

import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.entity.Usuario;
import br.com.leonardo.atividade_elotech.enums.Status;
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
public class EmprestimoDTO {

    private long id;

    private UsuarioDTO usuarioDTO;

    private LivroDTO livroDTO;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

}
