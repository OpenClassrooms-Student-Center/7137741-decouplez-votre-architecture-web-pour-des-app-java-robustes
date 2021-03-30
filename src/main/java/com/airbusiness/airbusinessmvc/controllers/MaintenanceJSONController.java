package com.airbusiness.airbusinessmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airbusiness.airbusinessmvc.entities.MaintenanceIssue;
import com.airbusiness.airbusinessmvc.repositories.MaintenanceRepository;


@RestController
@RequestMapping(path = "v1/maintenance")
public class MaintenanceJSONController {
    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceJSONController(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }
    
    // localhost:8080/v1/maintenance/unfixed/
    @GetMapping(path="/unfixed", produces = "application/json")
 	public Iterable<MaintenanceIssue> MaintenanceForm( Model model) {
        return maintenanceRepository.findByFixed("");
 	}
}
