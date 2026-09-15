package com.medicore.app.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicore.app.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

    boolean existsByDateTime(OffsetDateTime dateTime);
    List<Appointment> findByPatient_DocumentNumber(String documentNumber);
    List<Appointment> findByDateTimeAndPatient_DocumentNumber(OffsetDateTime dateTime, String documentNumber);
    Appointment findByIsAvailableAndDateTime(boolean available, OffsetDateTime dateTime);
    List<Appointment> findByIsAvailable(boolean available);
    List<Appointment> findByIsAvailableAndEmployeeIsNotNull(boolean available);
    
}
