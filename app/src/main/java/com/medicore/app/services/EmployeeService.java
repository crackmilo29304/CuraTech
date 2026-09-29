package com.medicore.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicore.app.models.Employee;
import com.medicore.app.models.Role;
import com.medicore.app.repository.EmployeeRepository;
import com.medicore.app.repository.RoleRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public boolean deleteEmployeeByDocumentNumber(String documentNumber) {
        int deletedCount = employeeRepository.deleteByDocumentNumber(documentNumber);
        return deletedCount > 0;
    }

    public boolean saveEmployee(Employee employee){
        if (employeeRepository.findByDocumentNumber(employee.getDocumentNumber()) != null) {
            //implement exception
            return false; // Employee with the same document number already exists
        }
        employeeRepository.save(employee);
        return true;
    }

    public Employee getEmployeeByDocumentNumber(String documentNumber) {
        return employeeRepository.findByDocumentNumber(documentNumber);
    }

    public List<Employee> getEmployeeByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    public boolean checkDataFormat(Employee employee) {
        // documentNumber must contain only numbers
        if (!employee.getDocumentNumber().matches("\\d+")) {
            System.err.println("Error: El ID/Documento del empleado debe contener solo números.");
            return false;
        }

        String regexTexto = "[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+";
        if (!employee.getName().matches(regexTexto)) {
            System.err.println("Error: El nombre del empleado contiene caracteres inválidos.");
            return false;
        }
        if (!employee.getLastName().matches(regexTexto)) {
            System.err.println("Error: El apellido del empleado contiene caracteres inválidos.");
            return false;
        }

        // 3. Email: standard email format
        if (employee.getEmail() != null && !employee.getEmail().trim().isEmpty()) {
            if (!employee.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                System.err.println("Error: El formato del correo electrónico del employee es incorrecto.");
                return false;
            }
        }

        if (employee.getBirthDate() != null) {
            if (employee.getBirthDate().isAfter(java.time.LocalDate.now().minusYears(18))) {
                System.err.println("Error: El employee debe ser mayor de edad.");
                return false;
            }
        }
        return true;
    }
}
