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
@Table(name = "branches")
@Entity
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    @Column(name = "branch_name", nullable = false)
    private String facilityName;
    
    @Column(name = "operating_hours", nullable = false)
    private String schedule;
    
    @Column(name = "hospital_level", nullable = false)
    private String hospitalLevel;

    @Column(name = "image_name", nullable = false)
    private String image;

}