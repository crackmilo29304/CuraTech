package com.medicore.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medications")
@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //DB
    
    @Column(name = "medication_name", nullable = false)
    private String name;
    
    @Column(name = "quantity_per_unit", nullable = false)
    private String quantityPerUnit;
    
    @Column(name = "price_cop", nullable = false)
    private int priceCOP;
    
    
}
