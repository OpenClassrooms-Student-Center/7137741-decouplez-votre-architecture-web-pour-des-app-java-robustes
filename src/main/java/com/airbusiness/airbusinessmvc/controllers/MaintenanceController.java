package com.airbusiness.airbusinessmvc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airbusiness.airbusinessmvc.entities.MaintenanceIssue;
import com.airbusiness.airbusinessmvc.repositories.MaintenanceRepository;


@Controller
public class MaintenanceController {
    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceController(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }
	
    @GetMapping("/new-issue")
    public String showNewIssueForm(MaintenanceIssue maintenance) {
        return "add-maintenance";
    }
    
    @PostMapping("/maintenance/add")
    public String addMaintenance(@Valid MaintenanceIssue maintenance, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-maintenance";
        }
        
        maintenanceRepository.save(maintenance);
        model.addAttribute("maintenance", maintenanceRepository.findAll());
        return "maintenance";
    }
    
    @GetMapping("/maintenance/edit/{id}")
    public String showUpdateMaintenanceForm(@PathVariable("id") long id, Model model) {
    	MaintenanceIssue maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid maintenance Id:" + id));
        model.addAttribute("maintenance", maintenance);
        return "update-maintenance";
    }
    
    @PostMapping("/maintenance/update/{id}")
    public String updateMaintenance(@PathVariable("id") long id, @Valid MaintenanceIssue maintenance, BindingResult result, Model model) {
        if (result.hasErrors()) {
            maintenance.setId(id);
            return "update-maintenance";
        }
        
        maintenanceRepository.save(maintenance);
        model.addAttribute("maintenance", maintenanceRepository.findAll());
        return "maintenance";
    }
    
    @GetMapping("/maintenance/delete/{id}")
    public String deleteMaintenance(@PathVariable("id") long id, Model model) {
    	MaintenanceIssue maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid maintenance Id:" + id));
        maintenanceRepository.delete(maintenance);
        model.addAttribute("maintenance", maintenanceRepository.findAll());
        return "maintenance";
    }
    
 	@RequestMapping("/maintenance")
 	public String maintenanceForm( Model model) {
 		model.addAttribute("maintenance", maintenanceRepository.findAll());
 		return "maintenance";
 	}
}
