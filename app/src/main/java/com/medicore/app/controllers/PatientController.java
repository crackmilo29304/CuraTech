package com.medicore.app.controllers;

import java.time.LocalDate;
import java.util.List;

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
import com.medicore.app.models.ApptmType;
import com.medicore.app.models.Employee;
import com.medicore.app.models.Facility;
import com.medicore.app.models.Patient;
import com.medicore.app.models.Pqrs;
import com.medicore.app.models.Prescription;
import com.medicore.app.services.AppointmentService;
import com.medicore.app.services.EmployeeService;
import com.medicore.app.services.FacilityService;
import com.medicore.app.services.PatientService;
import com.medicore.app.services.PrescriptionService; 
import com.medicore.app.utils.UserSession;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PrescriptionService prescriptionService;


    @GetMapping
    public String showPatientMenu() {
        return "patient/patientMenu"; // Busca home.html en templates/
    }
    @GetMapping("/updatePersonalDataView")
    public String showUpdatePersonalDataView(Model model) {
        System.err.println("Document Number: " + UserSession.getDocumentNumber());
        Patient patient = patientService.getPatientByDocumentNumber(UserSession.getDocumentNumber());
        model.addAttribute("patient", patient);
        return "patient/updatePersonalData"; // Busca updatePersonalData.html en templates/
    }
    @PostMapping("/updatePersonalData")
    public String updatePersonalData(@Valid @ModelAttribute Patient patient, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "patient/updatePersonalData"; // Si hay errores de validación, vuelve a la vista de actualización
            
        }
        if(patientService.updatePatient(patient)){
            return "redirect:/patient/updatePersonalDataView";
        }
         throw new RuntimeException("Error al actualizar los datos del paciente");
    }
    @GetMapping("/activePrescriptions")
    public String showActivePrescriptionsView(Model model) {
        List<Prescription> activePrescriptions = prescriptionService.getActivePrescriptionsByPatient(UserSession.getDocumentNumber());
        System.out.println("Número de documento: " + UserSession.getDocumentNumber());
        for (Prescription prescription : activePrescriptions) {
            System.out.println("Prescription ID: " + prescription.getId());
        }
        model.addAttribute("activePrescriptions", activePrescriptions);
        return "patient/activePrescriptions"; // Busca activePrescriptions.html en templates/
    }
    
    @GetMapping("/pqrs")
    public String showPQRSView(Model model) {
        Pqrs pqrs = new Pqrs();
        List<Pqrs> pqrsList = patientService.getPqrsByPatient(UserSession.getDocumentNumber());
        model.addAttribute("pqrsList", pqrsList);
        model.addAttribute("pqrs", pqrs);
        return "patient/pqrs"; // Busca pqrs.html en templates/
    }
    @PostMapping("/save-pqrs")
    public String savePqrs(@Valid @ModelAttribute Pqrs pqrs, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors() );
            return "patient/pqrs";
        }
        Patient patient = patientService.getPatientByDocumentNumber(UserSession.getDocumentNumber());
        pqrs.setPatient(patient);
        LocalDate currentDate = LocalDate.now();
        pqrs.setDate(currentDate);
        pqrs.setState("Pendiente"); // Establece el estado inicial
        patientService.savePqrs(pqrs);

        return "redirect:/patient/pqrs";
    }
    
    @GetMapping("/branches")
    public String showBranchesView() {
        return "patient/branches"; // Busca branches.html en templates/
    }
    @GetMapping("/appointments/schedule")
    public String showScheduleView(Model model) {
        List<ApptmType> apptmTypes = appointmentService.getAppointmentsTypes();
        List<Employee> doctors = employeeService.getAll();
        List<Facility> branches = facilityService.getAllFacilities();
        List<Appointment> appointments = appointmentService.getAllAppointments();

        model.addAttribute("apptmTypes", apptmTypes);
        model.addAttribute("doctors", doctors);
        model.addAttribute("branches", branches);
        model.addAttribute("appointments", appointments);
        return "patient/appointments/schedule"; // Busca schedule.html en templates/
    }
    @GetMapping("/appointments/cancel")
    public String showCancelView() {
        return "patient/appointments/cancel"; // Busca cancel.html en templates/
    }

    @GetMapping("/available-times")
    public String getAvailableTimes(@RequestParam String apptmTypeId, @RequestParam String doctorId, @RequestParam String date, Model model) {
        System.out.println("apptmTypeId: " + apptmTypeId);
        System.out.println("doctorId: " + doctorId);
        System.out.println("date: " + date);
        List<Appointment> availableTimes = appointmentService.getAvailableTimes(apptmTypeId, doctorId, date);
        model.addAttribute("availableTimes", availableTimes);
        return "patient/appointments/timesAvailable :: timesContainer";
    }
}
