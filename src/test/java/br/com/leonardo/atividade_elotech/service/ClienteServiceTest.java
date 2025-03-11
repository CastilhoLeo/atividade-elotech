package br.com.leonardo.atividade_elotech.service;


import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.builder.EntityBuilder;
import br.com.leonardo.atividade_elotech.converter.ClienteConverter;
import br.com.leonardo.atividade_elotech.dto.ClienteDTO;
import br.com.leonardo.atividade_elotech.entity.Cliente;
import br.com.leonardo.atividade_elotech.exception.ClienteNaoEncontradoException;
import br.com.leonardo.atividade_elotech.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteConverter clienteConverter;

    @InjectMocks
    private ClienteService clienteService;


    @Test
    public void cadastrarcliente_DeveRetornarUmclienteSalvo() {

        ClienteDTO clienteDTO = DTOBuilder.clienteDTO();
        Cliente cliente = EntityBuilder.cliente();

        Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
        Mockito.when(clienteConverter.toDto(cliente)).thenReturn(clienteDTO);
        Mockito.when(clienteConverter.toEntity(any(ClienteDTO.class))).thenReturn(cliente);

        ClienteDTO clienteSalvo = clienteService.cadastrarcliente(clienteDTO);

        Assertions.assertNotNull(clienteSalvo);
        Assertions.assertEquals(clienteSalvo.getClass(), ClienteDTO.class);
        Mockito.verify(clienteRepository, Mockito.times(1)).save(cliente);
        Mockito.verify(clienteConverter, Mockito.times(1)).toDto(cliente);
        Mockito.verify(clienteConverter, Mockito.times(1)).toEntity(clienteDTO);

    }

    @Test
    public void localizarPeloId_DeveRetornarOclienteBuscadoPeloId() {

        long clienteId = 1L;
        ClienteDTO clienteDTO = DTOBuilder.clienteDTO();
        Cliente cliente = EntityBuilder.cliente();

        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        Mockito.when(clienteConverter.toDto(any(Cliente.class))).thenReturn(clienteDTO);

        ClienteDTO clienteLocalizado = clienteService.localizarPeloId(clienteId);

        Assertions.assertNotNull(clienteLocalizado);
        Assertions.assertEquals(clienteLocalizado.getClass(), ClienteDTO.class);
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(clienteConverter, Mockito.times(1)).toDto(cliente);

    }

    @Test
    public void localizarPeloId_DeveRetornarclienteNaoEncontradoException() {

        long clienteId = 19L;

        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(ClienteNaoEncontradoException.class, ()->clienteService.localizarPeloId(clienteId));
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(19L);

    }

    @Test
    public void deletarcliente_DeveDeletarUmclientePeloId() {

        long clienteId = 1L;

        clienteService.deletarcliente(clienteId);

        Mockito.verify(clienteRepository, Mockito.times(1)).deleteById(1L);

    }

    @Test
    public void atualizarcliente_DeveAtualizarOsDadosDocliente(){

        long clienteId = 1L;
        Cliente cliente = EntityBuilder.cliente();
        ClienteDTO clienteDTO = DTOBuilder.clienteDTO();
                clienteDTO.setNome("Nome atualizado");
                clienteDTO.setEmail("atualizado@email.com");
                clienteDTO.setDataCadastro(LocalDate.of(2000, 10,10));
                clienteDTO.setTelefone("4499999999");


        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        Mockito.when(clienteConverter.toDto(any(Cliente.class))).thenReturn(clienteDTO);

        clienteService.atualizarcliente(clienteId, clienteDTO);

        Mockito.verify(clienteRepository, Mockito.times(1)).save(cliente);
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(clienteId);
        Assertions.assertEquals(cliente.getNome(), clienteDTO.getNome());
        Assertions.assertEquals(cliente.getTelefone(), clienteDTO.getTelefone());
        Assertions.assertEquals(cliente.getEmail(), clienteDTO.getEmail());
        Assertions.assertEquals(cliente.getDataCadastro(), clienteDTO.getDataCadastro());

    }

    @Test
    public void atualizarcliente_DeveRetornarclienteNaoEncontradoException() {

        long clienteId = 19L;

        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(ClienteNaoEncontradoException.class, () -> clienteService.localizarPeloId(clienteId));
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(19L);
    }

}
