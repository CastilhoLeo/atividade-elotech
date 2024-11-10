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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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


        emprestimo.setStatus(requestDevolucaoDTO.getStatus());
        if (requestDevolucaoDTO.getDataDevolucao().isAfter(emprestimo.getDataEmprestimo())) {
            emprestimo.setDataDevolucao(requestDevolucaoDTO.getDataDevolucao());
        } else {
            throw new DataDeDevolucaoException();
        }


        return emprestimoConverter.toDto(emprestimoRepository.save(emprestimo));
    }


    public boolean livroDisponivel(Long livroId){

        List<Emprestimo> emprestimos = emprestimoRepository.findByLivroId(livroId);

        Boolean indisponivel = emprestimos
                .stream()
                .map(e->e.getStatus())
                .anyMatch(s-> s.equals(Status.EMPRESTADO));

        if(emprestimos.isEmpty() || indisponivel==false){
            return true;
        }else{
            throw new LivroIndisponivelException();
        }

    }
}
