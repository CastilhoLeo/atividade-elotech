package br.com.leonardo.atividade_elotech.repository;

import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByUsuarioId(Long id);

    List<Emprestimo> findByLivroId(Long id);

    Page<Emprestimo> findByUsuarioNomeContainingOrLivroTituloContaining(String nome, String titulo, Pageable pageable);


}
