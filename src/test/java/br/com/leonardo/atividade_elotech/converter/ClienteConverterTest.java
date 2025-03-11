package br.com.leonardo.atividade_elotech.converter;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.builder.EntityBuilder;
import br.com.leonardo.atividade_elotech.dto.ClienteDTO;
import br.com.leonardo.atividade_elotech.entity.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class ClienteConverterTest {


    @Autowired
    private ClienteConverter clienteConverter;


    @Test
    public void toDto_deveConverterclienteEntityEmclienteDTO(){

        Cliente cliente = EntityBuilder.cliente();

        ClienteDTO clienteDTO = clienteConverter.toDto(cliente);

        Assertions.assertNotNull(clienteDTO);
        Assertions.assertEquals(ClienteDTO.class, clienteDTO.getClass());
        Assertions.assertEquals(clienteDTO.getId(), cliente.getId());
        Assertions.assertEquals(clienteDTO.getNome(), cliente.getNome());
        Assertions.assertEquals(clienteDTO.getTelefone(), cliente.getTelefone());
        Assertions.assertEquals(clienteDTO.getEmail(), cliente.getEmail());
        Assertions.assertEquals(clienteDTO.getDataCadastro(), cliente.getDataCadastro());

    }

    @Test
    public void toEntity_deveConverterUmclienteDTOEmclienteEntity(){

        ClienteDTO clienteDTO = DTOBuilder.clienteDTO();

        Cliente cliente = clienteConverter.toEntity(clienteDTO);

        Assertions.assertNotNull(cliente);
        Assertions.assertEquals(Cliente.class, cliente.getClass());
        Assertions.assertEquals(cliente.getId(), clienteDTO.getId());
        Assertions.assertEquals(cliente.getNome(), clienteDTO.getNome());
        Assertions.assertEquals(cliente.getTelefone(), clienteDTO.getTelefone());
        Assertions.assertEquals(cliente.getEmail(), clienteDTO.getEmail());
        Assertions.assertEquals(cliente.getDataCadastro(), clienteDTO.getDataCadastro());

    }
}
