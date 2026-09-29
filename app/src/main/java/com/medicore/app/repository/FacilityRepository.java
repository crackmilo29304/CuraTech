package com.medicore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicore.app.models.Facility;
@Repository 
public interface FacilityRepository extends JpaRepository<Facility, Integer>{
    
}
