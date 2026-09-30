package com.medicore.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String showAdminMenu() {
        return "admin/admin-menu"; // Busca home.html en templates/
    }
    @GetMapping("/register-employees")
    public String showRegisterEmployeesView() {
        return "admin/register-employees"; // Busca registerEmployees.html en templates/
    }

    @GetMapping("/search-employees")
    public String showSearchEmployeesView() {
        return "admin/search-employees"; // Busca searchEmployees.html en templates/
    }

    @GetMapping("/medicine-stock")
    public String showMedicineStockView() {
        return "admin/medicine-stock"; // Busca medicinesStock.html en templates/
    }
    @GetMapping("/delete-users")
    public String showDeleteUsersView() {
        return "admin/delete-users"; // Busca deleteUsers.html en templates/
    }
}
