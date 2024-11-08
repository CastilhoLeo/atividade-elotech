package br.com.leonardo.atividade_elotech.service;

import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.enums.Categoria;
import br.com.leonardo.atividade_elotech.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendacaoLivroService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public List<Categoria> categoriasDoUsuario(Long usuarioId){
        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioId(usuarioId);

        List<Categoria> categorias = emprestimos
                .stream()
                .map(e->e.getLivro().getCategoria())
                .distinct()
                .toList();

        return categorias;
    }


}
