package com.medicore.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicore.app.models.Prescription;
import com.medicore.app.repository.PrescriptionRepository;

@Service
public class PrescriptionService {
    @Autowired 
    private PrescriptionRepository prescriptionRepository;
    
    public boolean savePrescription(Prescription prescription) {
        prescriptionRepository.save(prescription);
        return true;
    }
}
