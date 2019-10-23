package com.andres.customermicro.dto;

import com.andres.customermicro.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateRequest {

    @NotNull
    String nombre;
    @NotNull
    String apellido;

    //no deberia ser necesario guardar la edad si tenemos la fecha de nacimiento
    //salvo casos particulares, como un cache
    //Integer edad;

    @NotNull
    String fechaNacimiento;

    public Cliente toEntity() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Cliente cliente = new Cliente();
        cliente.setNombre(this.nombre);
        cliente.setApellido(this.apellido);

        if (fechaNacimiento != null) {
            cliente.setFechaNacimiento(LocalDate.parse(fechaNacimiento, formatter));
        }

        return cliente;
    }
}
