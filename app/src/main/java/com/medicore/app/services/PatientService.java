package com.medicore.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicore.app.models.Patient;
import com.medicore.app.models.Pqrs;
import com.medicore.app.repository.PatientRepository;
import com.medicore.app.repository.PqrsRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PqrsRepository pqrsRepository;

    public boolean savePatient(Patient patient){
        if(patientRepository.findByDocumentNumber(patient.getDocumentNumber()).isPresent()){
            //implement exception
            System.out.println("El paciente ya existe");
            return false;
        }
        patientRepository.save(patient);
        return true;
    }
    public boolean updatePatient(Patient patient){
        if(!patientRepository.findByDocumentNumber(patient.getDocumentNumber()).isPresent()){
            //implement exception
            System.out.println("El paciente no existe");
            return false;
        }
        patientRepository.save(patient);
        return true;
    }
    public boolean deletePatient(String documentNumber){
        if(!patientRepository.findByDocumentNumber(documentNumber).isPresent()){
            //implement exception
            System.out.println("El paciente no existe");
            return false;
        }
        patientRepository.deleteByDocumentNumber(documentNumber);
        return true;
    }
    public Patient getPatientByDocumentNumber(String documentNumber){
        return patientRepository.findByDocumentNumber(documentNumber).orElse(null);
    }

    public List<Patient> getPatientByLastName(String lastName){
        return patientRepository.findByLastName(lastName);
    }


    public boolean checkAtributesLenght(Patient patient) {
    // VARCHAR 50
    if (patient.getName() != null && patient.getName().length() > 50) {
        return false;
    }

    if (patient.getLastName() != null && patient.getLastName().length() > 50) {
        return false;
    }

    if (patient.getDocumentNumber() != null && patient.getDocumentNumber().length() > 50) {
        return false;
    }

    if (patient.getAllergies() != null && patient.getAllergies().length() > 200) {
        return false;
    }

    if (patient.getBloodType() != null && patient.getBloodType().length() > 3) {
        return false;
    }

    if (patient.getEmail() != null && patient.getEmail().length() > 100) {
        return false;
    }
    return true;
}

    public boolean checkDataFormat(Patient patient){
        // only numbers
        if (!patient.getDocumentNumber().matches("\\d+")) {
            System.err.println("Error: El número de documento debe contener solo números.");
            return false;
        }

        // Only letters, spaces, and accents
        String regexTexto = "[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+";
        if (!patient.getName().matches(regexTexto)) {
            System.err.println("Error: El nombre contiene caracteres inválidos.");
            return false;
        }
        if (!patient.getLastName().matches(regexTexto)) {
            System.err.println("Error: El apellido contiene caracteres inválidos.");
            return false;
        }

        if (!patient.getBloodType().matches("^(A|B|AB|O)[+-]$")) {
            System.err.println("Error: El tipo de sangre no tiene un formato válido (Ej: A+, O-).");
            return false;
        }

        // 3. optional fields

        // Only letters, spaces, and accents
        if (patient.getAllergies() != null && !patient.getAllergies().trim().isEmpty()) {
            if (!patient.getAllergies().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s,.]+")) {
                System.err.println("Error: El campo de alergias contiene caracteres no permitidos.");
                return false;
            }
        }

        // Email format
        if (patient.getEmail() != null && !patient.getEmail().trim().isEmpty()) {
            if (!patient.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                System.err.println("Error: El formato del correo electrónico es incorrecto.");
                return false;
            }
        }

        return true;
    }

    public boolean savePqrs(Pqrs pqrs){
        pqrsRepository.save(pqrs);
        return true;
    }
    public List<Pqrs> getPqrsByPatient(String documentNumber) {
        return pqrsRepository.findByPatientDocumentNumber(documentNumber);
       
    }
    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }
    public Optional<Patient> searchPatientsByDocument(String document) {
        return patientRepository.findByDocumentNumber(document);
    }
}
