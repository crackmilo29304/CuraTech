package com.medicore.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.medicore.app.models.Appointment;
import com.medicore.app.models.Medicine;
import com.medicore.app.models.Prescription;
import com.medicore.app.services.AppointmentService;
import com.medicore.app.services.MedicineService;
import com.medicore.app.services.PrescriptionService;


@Controller
public class EmployeeController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/employee")
    public String showEmployeeMenu() {
        return "employee/employeeMenu"; // Busca home.html en templates/
    }

    @GetMapping("/employee/registerPatients")
    public String showRegisterPatientsView() {
        return "employee/registerPatients"; // Busca registerPatients.html en templates/
    }
    @GetMapping("/employee/searchPatients")
    public String showSearchPatientsView() {
        return "employee/searchPatients"; // Busca searchPatients.html en templates/
    }
    @GetMapping("/employee/createPrescription")
    public String showCreatePrescriptionView(Model model) {
        List<Appointment> appointments = appointmentService.getScheduledAppointmentsByDoctor();
        List<Medicine> medicines = medicineService.getAllMedicines();
        model.addAttribute("appointments", appointments);
        model.addAttribute("medicines", medicines);
        return "employee/createPrescription"; // Busca createPrescription.html en templates/
    }
    @PostMapping("/employee/createPrescription")
    public String savePrescription(@ModelAttribute Prescription prescription, BindingResult bindingResult ,Model model ) {
        if(bindingResult.hasErrors()){
            return "employee/createPrescription";
        }
        
        prescriptionService.savePrescription(prescription);
        return "redirect:/employee/createPrescription";
               
    }
    
}
