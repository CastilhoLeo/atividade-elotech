package br.com.leonardo.atividade_elotech.service;

import br.com.leonardo.atividade_elotech.converter.EmprestimoConverter;
import br.com.leonardo.atividade_elotech.dto.EmprestimoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestDevolucaoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestEmprestimoDTO;
import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.entity.Usuario;
import br.com.leonardo.atividade_elotech.enums.Status;
import br.com.leonardo.atividade_elotech.exception.*;
import br.com.leonardo.atividade_elotech.repository.EmprestimoRepository;
import br.com.leonardo.atividade_elotech.repository.LivroRepository;
import br.com.leonardo.atividade_elotech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe responsável pela implementação das regras ne negócio referente ao empréstimo dos livros
 */
@Service
public class EmprestimoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private EmprestimoConverter emprestimoConverter;


    public Page<EmprestimoDTO> pesquisarTodos(Pageable pageable){

        Page<Emprestimo> todosEmprestimos = emprestimoRepository.findAll(pageable);

        return todosEmprestimos.map(e->emprestimoConverter.toDto(e));
    }


    public EmprestimoDTO cadastrarEmprestimo(RequestEmprestimoDTO request){

            livroDisponivel(request.getLivroId());

            Livro livro = livroRepository.findById(request.getLivroId())
                    .orElseThrow(() -> new LivroNaoEncontradoException());

            Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new UsuarioNaoEncontradoException());

            Emprestimo emprestimo = new Emprestimo();

            emprestimo.setDataEmprestimo(request.getEmprestimoDTO().getDataEmprestimo());
            emprestimo.setUsuario(usuario);
            emprestimo.setLivro(livro);
            emprestimo.setStatus(Status.EMPRESTADO);


            return emprestimoConverter.toDto(emprestimoRepository.save(emprestimo));

    }

    public EmprestimoDTO devolverEmprestimo(Long id, RequestDevolucaoDTO requestDevolucaoDTO){

        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(()-> new EmprestimoNaoEncontradoException());

        if(emprestimo.getStatus().equals(Status.DEVOLVIDO)){  // lógica que não permite devolver um livro já devolvido
            throw new EmprestimoJaDevolvidoException();
        }

        emprestimo.setStatus(requestDevolucaoDTO.getStatus());  // altera status para devolvido


        // Lógica que não permite devolver livro com data igual ou inferior ao emprestimo
        if (requestDevolucaoDTO.getDataDevolucao().isAfter(emprestimo.getDataEmprestimo())) {

            emprestimo.setDataDevolucao(requestDevolucaoDTO.getDataDevolucao());

        } else {

            throw new DataDeDevolucaoException();
        }


        return emprestimoConverter.toDto(emprestimoRepository.save(emprestimo));
    }


    /**
     * Método que retorna true quando o livro não possui empréstimos com status "EMPRESTADO"
     * @param livroId
     * @return
     */
    public boolean livroDisponivel(Long livroId){

        List<Emprestimo> emprestimos = emprestimoRepository.findByLivroId(livroId);


        Boolean indisponivel = emprestimos              // retorna true se o livro estiver emprestado
                .stream()
                .map(e->e.getStatus())
                .anyMatch(s-> s.equals(Status.EMPRESTADO));

        if(emprestimos.isEmpty() || indisponivel==false){  // emprestimos é empty quando o livro nunca foi emprestado

            return true;

        }else{

            throw new LivroIndisponivelException();
        }

    }
}
