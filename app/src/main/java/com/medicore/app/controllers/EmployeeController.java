package com.medicore.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medicore.app.models.Appointment;
import com.medicore.app.models.Medicine;
import com.medicore.app.models.Patient;
import com.medicore.app.models.Prescription;
import com.medicore.app.services.AppointmentService;
import com.medicore.app.services.MedicineService;
import com.medicore.app.services.PatientService;

import jakarta.validation.Valid;




@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private MedicineService medicineService;
   @Autowired
    private PatientService patientService;
    @GetMapping
    public String showEmployeeMenu() {
        return "employee/employeeMenu"; // Busca home.html en templates/
    }

    @GetMapping("/registerPatients")
    public String showRegisterPatientsView(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);

        return "employee/registerPatients"; // Busca registerPatients.html en templates/
    }
    @PostMapping("/save-patient")
    public String savePatient(@Valid @ModelAttribute Patient patient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "employee/registerPatients";
        }
        
        patientService.savePatient(patient);
        
        return "redirect:/employee/registerPatients"; // Redirige a la vista de registro de pacientes después de guardar
    }
    
    @GetMapping("/search-patients-view")
    public String showSearchPatientsView() {
        return "employee/searchPatients"; // Busca searchPatients.html en templates/
    }
    @GetMapping("/search-patients")
    public String searchPatients(@RequestParam String document, @RequestParam String name, Model model) {
        if(document.isBlank()){
            List<Patient> foundPatients = patientService.searchPatientsByName(name);
            model.addAttribute("foundPatients", foundPatients);
        } else {
            Optional<Patient> foundPatient = patientService.searchPatientsByDocument(document);
            List<Patient> foundPatients = new ArrayList<>();
            foundPatients.add(foundPatient.orElse(null));
            model.addAttribute("foundPatients", foundPatients);
        }
        return "employee/searchPatients";
    }
    
    @GetMapping("/createPrescriptionView")
    public String showCreatePrescriptionView(Model model) {
        List<Appointment> appointments = appointmentService.getScheduledAppointmentsByDoctor();
        List<Medicine> medicines = medicineService.getAllMedicines();
        Prescription prescription = new Prescription();
        model.addAttribute("prescription", prescription);
        model.addAttribute("appointments", appointments);
        model.addAttribute("medicines", medicines);
        return "employee/createPrescription"; // Busca createPrescription.html en templates/
    }
   
    
}
