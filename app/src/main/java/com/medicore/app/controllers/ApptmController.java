package com.medicore.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicore.app.models.ApptmType;
import com.medicore.app.services.AppointmentService;




@RestController
@RequestMapping("/appointments")
public class ApptmController {
    @Autowired
    private AppointmentService appointmentService;
    
    @GetMapping("/types")
    public List<ApptmType> getAppointmentsTypes() {
        List<ApptmType> apptmTypes = appointmentService.getAppointmentsTypes();
        return apptmTypes;
    }
    
}
