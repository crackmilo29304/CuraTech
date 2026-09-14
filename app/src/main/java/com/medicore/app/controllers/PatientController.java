package com.medicore.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.medicore.app.models.ApptmType;
import com.medicore.app.models.Employee;
import com.medicore.app.models.Facility;
import com.medicore.app.services.AppointmentService;
import com.medicore.app.services.EmployeeService;
import com.medicore.app.services.FacilityService;

@Controller
public class PatientController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private FacilityService facilityService;


    @GetMapping("/patient")
    public String showPatientMenu() {
        return "patient/patientMenu"; // Busca home.html en templates/
    }
    @GetMapping("/patient/updatePersonalData")
    public String showUpdatePersonalDataView() {
        return "patient/updatePersonalData"; // Busca updatePersonalData.html en templates/
    }
    @GetMapping("/patient/activePrescriptions")
    public String showActivePrescriptionsView() {
        return "patient/activePrescriptions"; // Busca activePrescriptions.html en templates/
    }
    
    @GetMapping("/patient/pqrs")
    public String showPQRSView() {
        return "patient/pqrs"; // Busca pqrs.html en templates/
    }
    @GetMapping("/patient/branches")
    public String showBranchesView() {
        return "patient/branches"; // Busca branches.html en templates/
    }
    @GetMapping("/patient/appointments/schedule")
    public String showScheduleView(Model model) {
        List<ApptmType> apptmTypes = appointmentService.getAppointmentsTypes();
        List<Employee> doctors = employeeService.getAll();
        List<Facility> branches = facilityService.getAllFacilities();

        model.addAttribute("apptmTypes", apptmTypes);
        model.addAttribute("doctors", doctors);
        model.addAttribute("branches", branches);
        return "patient/appointments/schedule"; // Busca schedule.html en templates/
    }
    @GetMapping("/patient/appointments/cancel")
    public String showCancelView() {
        return "patient/appointments/cancel"; // Busca cancel.html en templates/
    }
}
