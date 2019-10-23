package com.andres.customermicro.service;

import com.andres.customermicro.data.KpiClientes;
import com.andres.customermicro.domain.Cliente;
import com.andres.customermicro.dto.ClienteCreateRequest;
import com.andres.customermicro.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void initUseCase() {
    }

    @Test
    void testAll() {
        //given
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("PEDRO", "SOLANO", LocalDate.of(1989, 9, 9)));
        clientes.add(new Cliente("JUAN", "SOTO", LocalDate.of(1979, 9, 9)));
        //when
        when(clienteRepository.findAll()).thenReturn(clientes);
        //then
        assertThat(clienteService.findAll()).isNotNull();
        assertThat(clienteService.findAll()).isEqualTo(clientes);
    }


    void createTest(){
        //given
        String firstName = "PEDRO";
        String lastName = "SOLANO";
        String birthday = "1989-09-30";
        ClienteCreateRequest clienteCreateRequest = new ClienteCreateRequest(firstName,lastName,birthday);

        Cliente cliente = clienteCreateRequest.toEntity();

        //when
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        //then
        assertThat(clienteService.create(clienteCreateRequest)).isNotNull();
    }


}
