package com.medicore.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String showAdminMenu() {
        return "admin/adminMenu"; // Busca home.html en templates/
    }
    @GetMapping("/registerEmployees")
    public String showRegisterEmployeesView() {
        return "admin/registerEmployees"; // Busca registerEmployees.html en templates/
    }

    @GetMapping("/searchEmployees")
    public String showSearchEmployeesView() {
        return "admin/searchEmployees"; // Busca searchEmployees.html en templates/
    }

    @GetMapping("/medicineStock")
    public String showMedicineStockView() {
        return "admin/medicineStock"; // Busca medicinesStock.html en templates/
    }
    @GetMapping("/deleteUsers")
    public String showDeleteUsersView() {
        return "admin/deleteUsers"; // Busca deleteUsers.html en templates/
    }
}
