package com.medicore.app.models;


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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prescriptions")
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //DB

    @NotNull(message = "Es obligatoria una cita asociada")
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "appointment_id", nullable = false) //FK
    private Appointment appointment;

    @NotNull(message = "El medicamento es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "medication_id", nullable = false) //FK
    private Medicine medicine;
 
    @Column(name="indications")
    private String directions;

    @NotBlank(message = "La dosis es obligatoria")
    private String dosage;
    
    @NotNull(message = "El batch stock asociado es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "batch_stock", nullable = false) //FK
    private BatchStock batchStock;
    
    @NotNull(message = "El campo activo no puede ser nulo")
    private boolean  active;


}
