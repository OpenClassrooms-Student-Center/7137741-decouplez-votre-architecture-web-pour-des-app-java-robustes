package com.airbusiness.airbusinessmvc.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class MaintenanceIssue {
	public static enum SubSystem {
		ENGINE("Engine"),
		FUSELAGE("Fuselage"),
		LEFT_WING("Left Wing"),
		LEFT_HORIZONAL_STABILIZER("Left Horizonal Stabilizer"),
		RIGHT_WING("Right Wing"),
		RIGHT_HORIZONAL_STABILIZER("Right Horizonal Stabilizer"),
		RUDDER("Rudder");
		
	    private final String displayValue;
	     
	    private SubSystem(String displayValue) {
	        this.displayValue = displayValue;
	    }
	     
	    public String getDisplayValue() {
	        return displayValue;
	    }
	}
	
	public static enum Level {
		MINOR("Minor"),
		MEDIUM("Medium"),
		SEVERE("Severe");
		
	    private final String displayValue;
	     
	    private Level(String displayValue) {
	        this.displayValue = displayValue;
	    }
	     
	    public String getDisplayValue() {
	        return displayValue;
	    }
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Entry Date is mandatory")
    private String entered;
    private String fixed;
    @NotBlank(message = "Details are mandatory")
    private String details;

    private SubSystem subSystem;
    private Level level;
   
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    
    public String getEntered() {
    	return entered;
    }
    public void setEntered(String entered) {
    	this.entered = entered;
    }
    
    public String getFixed() {
    	return fixed;
    }
    public void setFixed(String fixed) {
    	this.fixed = fixed;
    }
    
    public String getDetails() {
    	return details;
    }
    public void setDetails(String details) {
    	this.details = details;
    }
    
    public SubSystem getSubSystem() {
    	return subSystem;
    }
    public void setSubSystem(SubSystem subSystem) {
    	this.subSystem = subSystem;
    }
    
    public Level getLevel() {
    	return level;
    }
    public void setLevel(Level level) {
    	this.level = level;
    }
}
