package com.medicore.app.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
@Entity
public class Patient extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // DB
    
    @NotBlank(message = "Si no tiene ninguna alergia, escriba 'Ninguna'")
     @Pattern(
    regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", 
    message = "Las alergias solo deben contener letras y espacios"
    )
    private String allergies;

    @NotBlank(message = "El tipo de sangre es obligatorio")
    @Column(name = "blood_type", nullable = false)
    private String bloodType;
    
    @OneToMany(mappedBy= "patient", cascade = CascadeType.ALL )
    private List<Pqrs> pqrsList = new ArrayList<>();
/* 
   public Patient(PatientRegistrationDTO dto) {
        super(dto.lastname(), dto.birthDate(), dto.name(), dto.documentNumber());
        this.allergies = dto.allergies();
        this.bloodType = dto.bloodType();
    }
*/
    
}