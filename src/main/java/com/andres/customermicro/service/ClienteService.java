package com.andres.customermicro.service;

import com.andres.customermicro.data.KpiClientes;
import com.andres.customermicro.domain.Cliente;
import com.andres.customermicro.dto.ClienteCreateRequest;
import com.andres.customermicro.repository.ClienteRepository;
import com.andres.customermicro.util.StandartDeviationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.DoubleStream;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public KpiClientes kpiClientes(){
        Double promedioEdad = findAll()
                .stream()
                .mapToInt(Cliente::getEdad)
                .average().orElse(0);

        DoubleStream ageStream = findAll()
                .stream()
                .mapToDouble(Cliente::getEdad);

        double sd = StandartDeviationUtil.standardDeviation(ageStream.toArray());

        return new KpiClientes(promedioEdad, sd);
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteCreateRequest cliente){
        return clienteRepository.save(cliente.toEntity());
    }



}
