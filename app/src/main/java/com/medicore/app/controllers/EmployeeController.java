package com.medicore.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medicore.app.models.Appointment;
import com.medicore.app.models.Medicine;
import com.medicore.app.models.Prescription;
import com.medicore.app.services.AppointmentService;
import com.medicore.app.services.MedicineService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private MedicineService medicineService;
   
    @GetMapping
    public String showEmployeeMenu() {
        return "employee/employeeMenu"; // Busca home.html en templates/
    }

    @GetMapping("/registerPatients")
    public String showRegisterPatientsView() {
        return "employee/registerPatients"; // Busca registerPatients.html en templates/
    }
    @GetMapping("/searchPatients")
    public String showSearchPatientsView() {
        return "employee/searchPatients"; // Busca searchPatients.html en templates/
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
