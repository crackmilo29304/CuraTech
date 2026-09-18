package com.medicore.app.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicore.app.models.Appointment;
import com.medicore.app.models.ApptmType;
import com.medicore.app.models.Patient;
import com.medicore.app.repository.AppointmentRepository;
import com.medicore.app.repository.ApptmTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repo; 
    @Autowired
    private ApptmTypeRepository apptmTypeRepo;

    @Transactional
    public boolean scheduleAppointment(Appointment toSchedule, Patient patient) {
         if (toSchedule.isAvailable() == false) {
            //implement exception
            System.out.println("la cita no está disponible");
            return false;
         }

         toSchedule.setAvailable(false);
         toSchedule.setPatient(patient);

         return true;
    }
    public List<Appointment> getAllAppointments() {
        return repo.findAll();
    }
    public List<ApptmType> getAppointmentsTypes() {
        return apptmTypeRepo.findAll();
    }

    public static OffsetDateTime formatDateTime(LocalDate capturedDate, LocalTime time) {      
        LocalDateTime date = capturedDate.atTime(time);
        OffsetDateTime dateTime = date.atOffset(ZoneOffset.of("-05:00"));
        
        return dateTime;
    }

    @Transactional
    public boolean rescheduleAppointment(Appointment toCancel, Appointment toSchedule){
        if (toCancel.isAvailable() == true) {
            //implement exception
            System.out.println("la cita a reprogramar no está ocupada");
            return false;
        }
        if (toSchedule.isAvailable() == false) {
            //implement exception
            System.out.println("la cita que desea no está disponible");
            return false;
        }

        toCancel.setAvailable(true);
        toSchedule.setAvailable(false);

        toSchedule.setPatient(toCancel.getPatient());
        toCancel.setPatient(null);

        return true;
    }

    public boolean cancelAppointment(Appointment toCancel){
        toCancel.setAvailable(true);
        toCancel.setPatient(null);
        return true;
    }

    public List<Appointment> getAvailableAppointments() {
        return repo.findByIsAvailable(true);
    }

    public List<Appointment> getAppointmentsByPatient(Patient patient) {
        return repo.findByPatient_DocumentNumber(patient.getDocumentNumber());
    }

    public List<Appointment> getScheduledAppointmentsByDoctor() {
        return repo.findByIsAvailableAndEmployeeIsNotNull(false);
    }
    
}
