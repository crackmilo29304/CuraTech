package com.medicore.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicore.app.models.Facility;
import com.medicore.app.repository.FacilityRepository;

@Service
public class FacilityService {
    @Autowired
    private FacilityRepository facilityRepository;
    
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }
}
