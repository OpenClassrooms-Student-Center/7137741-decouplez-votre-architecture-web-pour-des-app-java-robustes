package com.airbusiness.airbusinessmvc.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    public String getFirstName() {
    	return firstName;
    }
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;
    public String getLastName() {
    	return lastName;
    }
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

	private String address;
    public String getAddress() {
    	return address;
    }
    public void setAddress(String address) {
    	this.address = address;
    }
    
	private String telephone;
    public String getTelephone() {
    	return telephone;
    }
    public void setTelephone(String telephone) {
    	this.telephone = telephone;
    }
    
	private String aircraftRating;
	public String getAircraftRating() {
		return aircraftRating;
	}
	public void setAircraftRating(String aircraftRating) {
		this.aircraftRating = aircraftRating;
	}
	
	private String arrivalAirport;
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	
	private String departureAirport;
	public String getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}
	
	private String licenseExpiration;
	public String getLicenseExpiration() {
		return licenseExpiration;
	}
	public void setLicenseExpiration(String licenseExpiration) {
		this.licenseExpiration = licenseExpiration;
	}
	
	private String licenseNumber;
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
}
