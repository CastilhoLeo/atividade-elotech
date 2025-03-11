package br.com.leonardo.atividade_elotech.service;

import br.com.leonardo.atividade_elotech.builder.DTOBuilder;
import br.com.leonardo.atividade_elotech.builder.EntityBuilder;
import br.com.leonardo.atividade_elotech.converter.EmprestimoConverter;
import br.com.leonardo.atividade_elotech.dto.EmprestimoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestDevolucaoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestEmprestimoDTO;
import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.entity.Cliente;
import br.com.leonardo.atividade_elotech.enums.Status;
import br.com.leonardo.atividade_elotech.exception.*;
import br.com.leonardo.atividade_elotech.repository.EmprestimoRepository;
import br.com.leonardo.atividade_elotech.repository.LivroRepository;
import br.com.leonardo.atividade_elotech.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EmprestimoServiceTest {

    @Mock
    private EmprestimoConverter emprestimoConverter;

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private EmprestimoService emprestimoService;

    private Pageable pageable;

    @Test
    public void pesquisarTodos_deveRetornarTodosOsEmprestimos(){


        Page<Emprestimo> emprestimos = new PageImpl<>(List.of(EntityBuilder.emprestimo()));
        EmprestimoDTO emprestimoDTO = DTOBuilder.emprestimoDTO();

        Mockito.when(emprestimoRepository.findAll(pageable)).thenReturn(emprestimos);
        Mockito.when(emprestimoConverter.toDto(any(Emprestimo.class))).thenReturn(emprestimoDTO);

        Page<EmprestimoDTO> emprestimosDTO = emprestimoService.pesquisarTodos(pageable);

        Mockito.verify(emprestimoRepository, Mockito.times(1)).findAll(pageable);
        Assertions.assertNotNull(emprestimosDTO);
    }



    @Test
    public void cadastrarEmprestimo_deveRetornarUmEmprestimoSalvo() {

        Livro livro = EntityBuilder.livro();
        Cliente cliente = EntityBuilder.cliente();
        RequestEmprestimoDTO requestEmprestimoDTO = DTOBuilder.requestEmprestimoDTO();
        EmprestimoDTO emprestimoDTO = DTOBuilder.emprestimoDTO();
        Emprestimo emprestimo1 = EntityBuilder.emprestimo();
        Emprestimo emprestimo2 = EntityBuilder.emprestimo();
        emprestimo2.setId(2L);
        Emprestimo emprestimo3 = EntityBuilder.emprestimo();
        emprestimo2.setId(3L);
        List<Emprestimo> emprestimos = List.of(emprestimo1, emprestimo2, emprestimo3);


        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        Mockito.when(emprestimoConverter.toDto(any(Emprestimo.class))).thenReturn(emprestimoDTO);
        Mockito.when(emprestimoRepository.save(any(Emprestimo.class))).thenReturn(emprestimo1);
        Mockito.when(emprestimoRepository.findByLivroId(anyLong())).thenReturn(emprestimos);
        ArgumentCaptor<Emprestimo> emprestimoCaptor = ArgumentCaptor.forClass(Emprestimo.class);


        EmprestimoDTO emprestimoDTOSalvo = emprestimoService.cadastrarEmprestimo(requestEmprestimoDTO);


        Assertions.assertEquals(emprestimoDTOSalvo.getClass(), EmprestimoDTO.class);
        Assertions.assertNotNull(emprestimoDTOSalvo);
        Mockito.verify(emprestimoRepository, Mockito.times(1)).save(emprestimoCaptor.capture());
        Assertions.assertNotNull(emprestimoCaptor.getValue());
        Assertions.assertEquals(emprestimoCaptor.getValue().getLivro(), livro);
        Assertions.assertEquals(emprestimoCaptor.getValue().getcliente(), cliente);
        Assertions.assertEquals(emprestimoCaptor.getValue().getDataEmprestimo(), LocalDate.of(2024, 11, 9));
        Assertions.assertEquals(emprestimoCaptor.getValue().getDataDevolucao(), null);
        Assertions.assertEquals(emprestimoCaptor.getValue().getStatus(), Status.EMPRESTADO);

    }

    @Test
    public void cadastrarEmprestimo_deveRetornarLivroNaoEncontradoException() {

        RequestEmprestimoDTO requestEmprestimoDTO = DTOBuilder.requestEmprestimoDTO();


        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.empty());


        Assertions.assertThrows(LivroNaoEncontradoException.class,
                () -> emprestimoService.cadastrarEmprestimo(requestEmprestimoDTO));
        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void cadastrarEmprestimo_deveRetornarclienteNaoEncontradoException() {

        Livro livro = EntityBuilder.livro();
        RequestEmprestimoDTO requestEmprestimoDTO = DTOBuilder.requestEmprestimoDTO();

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());


        Assertions.assertThrows(ClienteNaoEncontradoException.class,
                () -> emprestimoService.cadastrarEmprestimo(requestEmprestimoDTO));
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(1L);
    }



    @Test
    public void devolverEmprestimo_deveAlterarStatusEAtualizarEmprestimo() {

        long emprestimoId = 1L;
        LocalDate dataDevolucao = LocalDate.of(2024,11,11);
        Emprestimo emprestimo = EntityBuilder.emprestimo();
        emprestimo.setStatus(Status.EMPRESTADO);
        EmprestimoDTO emprestimoDTO = DTOBuilder.emprestimoDTO();


        Mockito.when(emprestimoRepository.findById(anyLong())).thenReturn(Optional.of(emprestimo));
        Mockito.when(emprestimoConverter.toDto(any(Emprestimo.class))).thenReturn(emprestimoDTO);
        Mockito.when(emprestimoRepository.save(any(Emprestimo.class))).thenReturn(emprestimo);
        ArgumentCaptor<Emprestimo> emprestimoCaptor = ArgumentCaptor.forClass(Emprestimo.class);

        emprestimoService.devolverEmprestimo(emprestimoId, dataDevolucao);

        Mockito.verify(emprestimoRepository, Mockito.times(1)).save(emprestimoCaptor.capture());
        Assertions.assertEquals(emprestimoCaptor.getValue().getStatus(), Status.DEVOLVIDO);
        Assertions.assertEquals(emprestimoCaptor.getValue().getDataDevolucao(), LocalDate.of(2024,11,11));
        Mockito.verify(emprestimoConverter, Mockito.times(1)).toDto(any(Emprestimo.class));
        Mockito.verify(emprestimoRepository, Mockito.times(1)).findById(emprestimoId);


    }

    @Test
    public void devolverEmprestmimo_deveRetornarDataDeDevolucaoException() {

        long emprestimoId = 1L;

        RequestDevolucaoDTO requestDevolucaoDTO = DTOBuilder.requestDevolucaoDTO();
        LocalDate dataDevolucao = LocalDate.of(2024, 1, 1);
        Emprestimo emprestimo = EntityBuilder.emprestimo();
        emprestimo.setStatus(Status.EMPRESTADO);


        Mockito.when(emprestimoRepository.findById(anyLong())).thenReturn(Optional.of(emprestimo));


        Assertions.assertThrows(DataDeDevolucaoException.class,
                () -> emprestimoService.devolverEmprestimo(1L, dataDevolucao));
    }

    @Test
    public void devolverEmprestmimo_deveRetornarEmprestimoJaDevolvidoException() {

        long emprestimoId = 1L;

        LocalDate dataDevolucao = LocalDate.of(2024, 11, 11);
        Emprestimo emprestimo = EntityBuilder.emprestimo(); // Status DEVOLVIDO


        Mockito.when(emprestimoRepository.findById(anyLong())).thenReturn(Optional.of(emprestimo));


        Assertions.assertThrows(EmprestimoJaDevolvidoException.class,
                () -> emprestimoService.devolverEmprestimo(1L, dataDevolucao));
    }

    @Test
    public void devolverEmprestimo_deveRetornarEmprestimoNaoEncontradoException() {

        long emprestimoId = 1L;
        LocalDate dataDevolucao = LocalDate.of(2024, 11, 11);


        Mockito.when(emprestimoRepository.findById(anyLong())).thenReturn(Optional.empty());


        Assertions.assertThrows(EmprestimoNaoEncontradoException.class,
                () -> emprestimoService.devolverEmprestimo(emprestimoId, dataDevolucao));


    }


    @Test
    public void livroDisponivel_deveRetornarTrueParaLivroDisponivel() {

        long livroId = 1L;
        Emprestimo emprestimo1 = EntityBuilder.emprestimo();
        Emprestimo emprestimo2 = EntityBuilder.emprestimo();
        emprestimo2.setId(2L);
        List<Emprestimo> emprestimos = List.of(emprestimo1, emprestimo2);


        Mockito.when(emprestimoRepository.findByLivroId(anyLong())).thenReturn(emprestimos);


        Boolean resultado = emprestimoService.livroDisponivel(livroId);


        Assertions.assertEquals(resultado, true);
    }

    @Test
    public void livroDisponivel_deveRetornarTrueParaLivroDisponivelComListaDeEmprestimosVazia() {

        long livroId = 1L;
        List<Emprestimo> emprestimos = List.of();


        Mockito.when(emprestimoRepository.findByLivroId(anyLong())).thenReturn(emprestimos);


        Boolean resultado = emprestimoService.livroDisponivel(livroId);


        Assertions.assertEquals(resultado, true);

    }

    @Test
    public void livroDisponivel_deveRetornarLivroIndisponivelException() {

        long livroId = 1L;
        Emprestimo emprestimo1 = EntityBuilder.emprestimo();
        Emprestimo emprestimo2 = EntityBuilder.emprestimo();
        emprestimo2.setId(2L);
        emprestimo2.setStatus(Status.EMPRESTADO);
        List<Emprestimo> emprestimos = List.of(emprestimo1, emprestimo2);


        Mockito.when(emprestimoRepository.findByLivroId(anyLong())).thenReturn(emprestimos);


        Assertions.assertThrows(LivroIndisponivelException.class,()-> emprestimoService.livroDisponivel(livroId));
    }
}
