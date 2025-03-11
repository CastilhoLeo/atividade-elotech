package br.com.leonardo.atividade_elotech.service;

import br.com.leonardo.atividade_elotech.converter.ClienteConverter;
import br.com.leonardo.atividade_elotech.dto.ClienteDTO;
import br.com.leonardo.atividade_elotech.entity.Cliente;
import br.com.leonardo.atividade_elotech.exception.ClienteNaoEncontradoException;
import br.com.leonardo.atividade_elotech.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteConverter clienteConverter;


    public ClienteDTO cadastrarcliente(ClienteDTO clienteDTO){

        Cliente cliente = clienteConverter.toEntity(clienteDTO);

         return clienteConverter.toDto(clienteRepository.save(cliente));
    }


    public ClienteDTO localizarPeloId(Long id){

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new ClienteNaoEncontradoException());

        return clienteConverter.toDto(cliente);
    }

    public List<ClienteDTO> localizarNome(String nome){

            List<Cliente> clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);
            return clientes.stream()
                    .map((u)-> clienteConverter.toDto(u))
                    .toList();


    }


    public void deletarcliente(Long id){

        clienteRepository.deleteById(id);
    }


    public ClienteDTO atualizarcliente(Long id, ClienteDTO clienteDTO){

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new ClienteNaoEncontradoException());

        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setDataCadastro(clienteDTO.getDataCadastro());

        return clienteConverter.toDto(clienteRepository.save(cliente));

    }
}
