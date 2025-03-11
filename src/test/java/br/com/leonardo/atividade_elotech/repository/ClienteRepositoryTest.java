package br.com.leonardo.atividade_elotech.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;


}
