package br.com.leonardo.atividade_elotech.converter;

import br.com.leonardo.atividade_elotech.dto.ClienteDTO;
import br.com.leonardo.atividade_elotech.entity.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Realiza a conversão entre as classes cliente e clienteDTO
 */

@Component
public class ClienteConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ClienteDTO toDto(Cliente cliente){
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public Cliente toEntity(ClienteDTO clienteDTO){
        return modelMapper.map(clienteDTO, Cliente.class);
    }
}
