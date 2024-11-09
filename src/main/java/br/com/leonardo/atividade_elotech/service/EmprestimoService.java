package br.com.leonardo.atividade_elotech.service;

import br.com.leonardo.atividade_elotech.converter.EmprestimoConverter;
import br.com.leonardo.atividade_elotech.dto.EmprestimoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestDevolucaoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestEmprestimoDTO;
import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.entity.Usuario;
import br.com.leonardo.atividade_elotech.enums.Status;
import br.com.leonardo.atividade_elotech.exception.EmprestimoNaoEncontradoException;
import br.com.leonardo.atividade_elotech.exception.LivroNaoEncontradoException;
import br.com.leonardo.atividade_elotech.exception.UsuarioNaoEncontradoException;
import br.com.leonardo.atividade_elotech.repository.EmprestimoRepository;
import br.com.leonardo.atividade_elotech.repository.LivroRepository;
import br.com.leonardo.atividade_elotech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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


    public EmprestimoDTO cadastrarEmprestimo(RequestEmprestimoDTO request){

        Livro livro = livroRepository.findById(request.getLivroId())
                .orElseThrow(()-> new LivroNaoEncontradoException());

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(()->new UsuarioNaoEncontradoException());

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

        emprestimo.setDataDevolucao(requestDevolucaoDTO.getDataDevolucao());
        emprestimo.setStatus(Status.DEVOLVIDO);

        return emprestimoConverter.toDto(emprestimoRepository.save(emprestimo));
    }
}
