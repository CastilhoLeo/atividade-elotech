package br.com.leonardo.atividade_elotech.service;

import br.com.leonardo.atividade_elotech.converter.LivroConverter;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.enums.Categoria;
import br.com.leonardo.atividade_elotech.repository.EmprestimoRepository;
import br.com.leonardo.atividade_elotech.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe que implementa a regra de negócio para seugestão de livros para os usuários, com base nos livros já emprestados
 */

@Service
public class RecomendacaoLivroService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroConverter livroConverter;


    /**
     * Método que retorna a recomendação de livros para o usuário com base nas categorias já alugadas anteriormente
     * e excluindo os livros já alugados
     * @param usuarioId
     * @return
     */

    public List<LivroDTO> recomendacoesDoUsuario(Long usuarioId){

        List<Emprestimo> emprestimosDoUsuario = emprestimoRepository.findByUsuarioId(usuarioId);

        List<Livro> livrosDoUsuario = emprestimosDoUsuario  //Livros que o usuário já emprestou
                .stream()
                .map(e->e.getLivro()).toList();

        List<Categoria> categorias = livrosDoUsuario  // Cria um list das categorias já alugados pelo usuário
                .stream()
                .map(e->e.getCategoria())
                .distinct()
                .toList();

        List<Livro> livrosRecomendados = livrosDaCategoria(categorias); // Busca no BD somente livros com categorias do usuário

        // Exclui do resultado os livros já emprestados pelo usuário
        List<Livro> livrosRecomendadosFiltrado = retiraLivrosJaEmprestados(livrosDoUsuario, livrosRecomendados);

        return livrosRecomendadosFiltrado.stream().map(l->livroConverter.toDto(l)).toList();
    }

    /**
     * Método que busca no repository os livros com base no list das categorias passadas como parâmetro
     * @param categorias
     * @return
     */
    private List<Livro> livrosDaCategoria(List<Categoria> categorias){

        List<Livro> livros = livroRepository.findByCategoriaIn(categorias);

        return livros;
    }

    /**
     * Método exclui da lista de livros recomendados os livros já emprestados anteriormente pelo usuário
     * @param livrosDoUsuario
     * @param livrosRecomendados
     * @return
     */

    private List<Livro> retiraLivrosJaEmprestados(List<Livro> livrosDoUsuario, List<Livro> livrosRecomendados){

        livrosRecomendados.removeAll(livrosDoUsuario);

        return livrosRecomendados;
    }


}
