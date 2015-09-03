package com.inflow.banking.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date date;
    
    public Event() {}

    public Event(Long id, String name, String description, Date date) {
	this.id = id;
	this.name = name;
	this.description = description;
	this.date = date;
    }

    public void listeners(){
	System.out.println("Listeners Here");
    }
    
    @Override
    public String toString() {
        
        return this.id + this.name + this.description + this.date.toString();
    }

}
