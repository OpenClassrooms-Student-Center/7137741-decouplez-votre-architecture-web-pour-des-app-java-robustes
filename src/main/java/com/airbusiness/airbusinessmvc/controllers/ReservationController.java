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

import com.airbusiness.airbusinessmvc.entities.Reservation;
import com.airbusiness.airbusinessmvc.repositories.ReservationRepository;


@Controller
public class ReservationController {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
	
    @GetMapping("/new-trip")
    public String showNewTripForm(Reservation user) {
        return "add-reservation";
    }
    
    @PostMapping("/reservation/add")
    public String addReservation(@Valid Reservation reservation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-reservation";
        }
        
        reservationRepository.save(reservation);
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservations";
    }
    
    @GetMapping("/reservation/edit/{id}")
    public String showUpdateReservationForm(@PathVariable("id") long id, Model model) {
    	Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + id));
        model.addAttribute("reservation", reservation);
        return "update-reservation";
    }
    
    @PostMapping("/reservation/update/{id}")
    public String updateReservation(@PathVariable("id") long id, @Valid Reservation reservation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            reservation.setId(id);
            return "update-reservation";
        }
        
        reservationRepository.save(reservation);
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservations";
    }
    
    @GetMapping("/reservation/delete/{id}")
    public String deleteReservation(@PathVariable("id") long id, Model model) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + id));
        reservationRepository.delete(reservation);
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservations";
    }
    
 	@RequestMapping("/reservations")
 	public String reservationsForm(Model model) {
 		model.addAttribute("reservations", reservationRepository.findAll());
 		return "reservations";
 	}
}
