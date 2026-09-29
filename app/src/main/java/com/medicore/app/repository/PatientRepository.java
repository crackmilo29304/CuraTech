package com.medicore.app.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.medicore.app.models.Patient;

import jakarta.transaction.Transactional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    // include optional to manage NullPointerException
    Optional<Patient> findByDocumentNumber(String documentNumber);

    List<Patient> findByLastName(String lastName);

    // these labels are obligatory to delete and update operations
    @Modifying
    @Transactional
    void deleteByDocumentNumber(String documentNumber);

    List<Patient> findByNameContainingIgnoreCase(String name);
}
