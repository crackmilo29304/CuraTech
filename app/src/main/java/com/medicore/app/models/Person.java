/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medicore.app.models;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class Person {
    @Column(name = "document_number", unique = true)
    private String documentNumber;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
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
