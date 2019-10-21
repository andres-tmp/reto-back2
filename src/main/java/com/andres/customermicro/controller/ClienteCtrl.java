package com.andres.customermicro.controller;

import com.andres.customermicro.data.KpiClientes;
import com.andres.customermicro.domain.Cliente;
import com.andres.customermicro.dto.ClienteCreateRequest;
import com.andres.customermicro.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping
public class ClienteCtrl {

    @Autowired
    ClienteService clienteService;

    @ApiOperation(value = "Devuelva la lista de clientes registrados")
    @GetMapping("/listclientes")
    public List<Cliente> findAll(){
        return clienteService.findAll();
    }

    @ApiOperation(value = "Devuelva indicadores de los clientes registrados")
    @GetMapping("/kpideclientes")
    public KpiClientes kpiClientes(){
        return clienteService.kpiClientes();
    }

    @ApiOperation(value = "Permite crear un cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente almacenado en la base de datos"),
            @ApiResponse(code = 400, message = "Request invalido"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/creacliente")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cliente create( @ApiParam(value = "Datos del cliente", required = true) @Valid @RequestBody ClienteCreateRequest request){
        return  clienteService.create(request);
    }


}
