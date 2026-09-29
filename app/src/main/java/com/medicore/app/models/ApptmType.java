package com.medicore.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments_types")
public class ApptmType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // database id

    
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="job_title")
    private Role role;

    /* 
    public boolean setTypeByRole(Role role) {
        if (role == null) {
            return false;
        }

        switch (role.getTitleName()) {
            case "Médico general":
                this.setType(Type.GENERAL_CHECK_UP);
                break;
            case "Médico urgencista":
                this.setType(Type.URGENT_CARE_VISIT);
                break;
            case "Odontólogo":
                this.setType(Type.DENTAL_CHECK_UP);
                break;
            default:
                return false;
        }

        this.setRole(role);
        return true;
    }
    */
}
