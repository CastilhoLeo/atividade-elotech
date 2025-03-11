package br.com.leonardo.atividade_elotech.converter;

import br.com.leonardo.atividade_elotech.dto.EmprestimoDTO;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.dto.ClienteDTO;
import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.entity.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Realiza a conversão entre as classes Emprestimo e EmprestimoDTO
 */

@Component
public class EmprestimoConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteConverter clienteConverter;

    @Autowired
    private LivroConverter livroConverter;

    public EmprestimoDTO toDto (Emprestimo emprestimo){

        ClienteDTO clienteDTO = clienteConverter.toDto(emprestimo.getCliente());
        LivroDTO livroDTO = livroConverter.toDto(emprestimo.getLivro());

        EmprestimoDTO emprestimoDTO = modelMapper.map(emprestimo, EmprestimoDTO.class);

        emprestimoDTO.setClienteDTO(clienteDTO);
        emprestimoDTO.setLivroDTO(livroDTO);

        return emprestimoDTO;

    }

    public Emprestimo toEntity (EmprestimoDTO emprestimoDTO){

        Cliente cliente = clienteConverter.toEntity(emprestimoDTO.getClienteDTO());
        Livro livro = livroConverter.toEntity(emprestimoDTO.getLivroDTO());

        Emprestimo emprestimo = modelMapper.map(emprestimoDTO, Emprestimo.class);

        emprestimo.setCliente(cliente);
        emprestimo.setLivro(livro);

        return emprestimo;
    }
}
