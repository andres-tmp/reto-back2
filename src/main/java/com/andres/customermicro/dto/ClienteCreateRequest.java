package com.andres.customermicro.dto;

import com.andres.customermicro.domain.Cliente;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ClienteCreateRequest {

    @NotNull
    String nombre;
    @NotNull
    String apellido;

    //no deberia ser necesario guardar la edad si tenemos la fecha de nacimiento
    //salvo casos particulares, como un cache
    //Integer edad;

    @NotNull
    LocalDate fechaNacimiento;

    public Cliente toEntity(){
        Cliente cliente = new Cliente();
        cliente.setNombre(this.nombre);
        cliente.setApellido(this.apellido);
        cliente.setFechaNacimiento(this.fechaNacimiento);
        return cliente;
    }
}
