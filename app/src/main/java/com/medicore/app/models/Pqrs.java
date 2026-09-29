package com.medicore.app.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "complaints")
@Entity
public class Pqrs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // DB

    @NotBlank (message = "El asunto es obligatorio")
    private String subject;
    @NotBlank (message = "La descripción es obligatoria")
    private String description;
    
    @NotBlank(message = "El tipo de solicitud es obligatorio")
    private String type;

    
    private String state;

    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "patient_id", nullable = false) //FK
    private Patient patient;

   
    @Column(name="created_at")
    private LocalDate date;


}
