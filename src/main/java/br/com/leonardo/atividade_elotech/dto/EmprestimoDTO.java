package br.com.leonardo.atividade_elotech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
