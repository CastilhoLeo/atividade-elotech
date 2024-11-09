package br.com.leonardo.atividade_elotech.repository;

import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByCategoriaIn (List<Categoria> categoria);

}
