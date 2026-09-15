/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medicore.app.models;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class Person {
    @NotBlank(message = "El número de documento es obligatorio")
    @Pattern(
    regexp = "^[0-9]{6,10}$", 
    message = "El documento debe contener entre 6 y 10 dígitos numéricos"
    )
    @Column(name = "document_number", unique = true)
    private String documentNumber;
    
    @NotBlank (message = "El nombre es obligatorio")
    @Pattern(
    regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", 
    message = "El nombre solo debe contener letras y espacios"
    )
    @Column(name = "first_name")
    private String name;
    
    @NotBlank (message = "El apellido es obligatorio")
    @Pattern(
    regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", 
    message = "El apellido solo debe contener letras y espacios"
    )
    @Column(name = "last_name")
    private String lastName;
    
    
    @Email(message = "El formato del correo electrónico no es válido")
    private String email;
    
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    
    public int getYearsOld(LocalDate birthDate){
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        int years = age.getYears();
        return years;
    }

    public void setBirthDate(LocalDate birthDate) {
        
        LocalDate today = LocalDate.now();
        if (birthDate.isAfter(today)) {
            System.err.println("La fecha de nacimiento no es correcta");
            return;
        }
        this.birthDate = birthDate;
    }

    
}
