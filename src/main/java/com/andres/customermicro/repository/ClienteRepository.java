package com.andres.customermicro.repository;

import com.andres.customermicro.domain.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    @Query("select c from Cliente c")
    List<Cliente> findAll();

}
