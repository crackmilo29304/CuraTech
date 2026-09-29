package com.medicore.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicore.app.models.Pqrs;

@Repository
public interface PqrsRepository extends JpaRepository<Pqrs, Integer> {

    List<Pqrs> findByPatientDocumentNumber(String documentNumber);
    
}
