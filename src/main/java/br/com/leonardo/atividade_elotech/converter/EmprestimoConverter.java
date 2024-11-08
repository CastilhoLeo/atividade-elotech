package br.com.leonardo.atividade_elotech.converter;

import br.com.leonardo.atividade_elotech.dto.EmprestimoDTO;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.dto.UsuarioDTO;
import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioConverter usuarioConverter;

    @Autowired
    private LivroConverter livroConverter;

    public EmprestimoDTO toDto (Emprestimo emprestimo){

        UsuarioDTO usuarioDTO = usuarioConverter.toDto(emprestimo.getUsuario());
        LivroDTO livroDTO = livroConverter.toDto(emprestimo.getLivro());

        EmprestimoDTO emprestimoDTO = modelMapper.map(emprestimo, EmprestimoDTO.class);

        emprestimoDTO.setUsuarioDTO(usuarioDTO);
        emprestimoDTO.setLivroDTO(livroDTO);

        return emprestimoDTO;

    }

    public Emprestimo toEntity (EmprestimoDTO emprestimoDTO){

        Usuario usuario = usuarioConverter.toEntity(emprestimoDTO.getUsuarioDTO());
        Livro livro = livroConverter.toEntity(emprestimoDTO.getLivroDTO());

        Emprestimo emprestimo = modelMapper.map(emprestimoDTO, Emprestimo.class);

        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        return emprestimo;
    }
}
