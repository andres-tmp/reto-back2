package com.andres.customermicro.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    int id;

    @NotNull
    String nombre;
    @NotNull
    String apellido;

    //no deberia ser necesario guardar la edad si tenemos la fecha de nacimiento
    //salvo casos particulares, como un cache
    @Transient
    Integer edad;

    @NotNull
    LocalDate fechaNacimiento;

    @PostLoad
    public void postLoad(){
        LocalDate currentDate = LocalDate.now();
        this.edad = Period.between(fechaNacimiento, currentDate).getYears();
    }
}
