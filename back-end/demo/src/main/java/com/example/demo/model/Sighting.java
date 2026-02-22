package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sightings")

public class Sighting {
@Id
    private String id; //id to avoid duplicates
    private LocalDateTime date;
    private String location;
    private String species;
    private String latinName;

    // Constructors, Getters and Setters  
    public Sighting() {}

    public Sighting(String id, LocalDateTime date, String location, String species, String latinName) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.species = species;
        this.latinName = latinName;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getLatinName() { return latinName; }
    public void setLatinName(String latinName) { this.latinName = latinName; }
}
