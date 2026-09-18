package com.medicore.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicore.app.models.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{
    List<Prescription> findByAppointment_Id(int appointmentId);
    List<Prescription> findByAppointmentPatientDocumentNumberAndActive(String documentNumber, boolean active);
}
