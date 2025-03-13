package br.com.leonardo.atividade_elotech.converter;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.builder.EntityBuilder;
import br.com.leonardo.atividade_elotech.dto.EmprestimoDTO;
import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class EmprestimoConverterTest {

    @Autowired
    private EmprestimoConverter emprestimoConverter;

    @Test
    public void toDto_deveConverterEmprestimoEntityEmEmprestimoDTO(){

        Emprestimo emprestimo = EntityBuilder.emprestimo();

        EmprestimoDTO emprestimoDTO = emprestimoConverter.toDto(emprestimo);

        Assertions.assertNotNull(emprestimoDTO);
        Assertions.assertEquals(EmprestimoDTO.class, emprestimoDTO.getClass());
        Assertions.assertEquals(emprestimo.getId(), emprestimoDTO.getId());
        Assertions.assertEquals(emprestimoDTO.getLivroDTO(), DTOBuilder.livroDTO());
        Assertions.assertEquals(emprestimoDTO.getClienteDTO(), DTOBuilder.clienteDTO());
        Assertions.assertEquals(emprestimo.getDataEmprestimo(), emprestimoDTO.getDataEmprestimo());
        Assertions.assertEquals(emprestimo.getDataDevolucao(), emprestimoDTO.getDataDevolucao());

    }


    @Test
    public void toEntity_deveConverterEmprestimoDTOEmEmprestimoEntity(){

        EmprestimoDTO emprestimoDTO = DTOBuilder.emprestimoDTO();

        Emprestimo emprestimo = emprestimoConverter.toEntity(emprestimoDTO);

        Assertions.assertNotNull(emprestimo);
        Assertions.assertEquals(Emprestimo.class, emprestimo.getClass());
        Assertions.assertEquals(emprestimo.getId(), emprestimoDTO.getId());
        Assertions.assertEquals(emprestimoDTO.getLivroDTO(), DTOBuilder.livroDTO());
        Assertions.assertEquals(emprestimoDTO.getClienteDTO(), DTOBuilder.clienteDTO());
        Assertions.assertEquals(emprestimo.getDataEmprestimo(), emprestimoDTO.getDataEmprestimo());
        Assertions.assertEquals(emprestimo.getDataDevolucao(), emprestimoDTO.getDataDevolucao());
        Assertions.assertEquals(emprestimo.getStatus(), Status.EMPRESTADO);

    }

}
