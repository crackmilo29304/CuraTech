package com.medicore.app.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicore.app.models.Appointment;
import com.medicore.app.models.ApptmType;
import com.medicore.app.models.Patient;
import com.medicore.app.repository.AppointmentRepository;
import com.medicore.app.repository.ApptmTypeRepository;
import com.medicore.app.repository.PatientRepository;
import com.medicore.app.utils.UserSession;

import jakarta.transaction.Transactional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repo; 
    @Autowired
    private ApptmTypeRepository apptmTypeRepo;
    @Autowired
    private PatientRepository patientRepo;

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
    public boolean scheduleAppointment(String apptmTypeId, String doctorId, String date, String time){
        LocalDate capturedDate = LocalDate.parse(date);
        LocalTime capturedTime = LocalTime.parse(time);
        capturedTime = capturedTime.minusHours(5);
        OffsetDateTime dateTime = formatDateTime(capturedDate, capturedTime);
        System.out.println("date time en el service: " + dateTime);
        Appointment toSchedule = repo.findByEmployeeIdAndDateTime(Integer.parseInt(doctorId), dateTime);
        Optional<Patient> patient = patientRepo.findByDocumentNumber(UserSession.getDocumentNumber());
        toSchedule.setPatient(patient.get());
        toSchedule.setAvailable(false);
        repo.save(toSchedule);
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
    public List<Appointment> getAvailableTimes(String apptmTypeId, String doctorId, String date) {
        return repo.findAppointmentsByDay( Integer.parseInt(apptmTypeId), Integer.parseInt(doctorId), LocalDate.parse(date));
    }
    
}
