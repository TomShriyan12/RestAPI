package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "mentors")
public class Mentor extends User {
    private String industry;
    private String expertise;

    @ElementCollection
    private List<String> skills;

    private boolean available;

    public Mentor() {}

    public Mentor(String name, String email, String password, String role, String industry, String expertise, List<String> skills, boolean available) {
        super(name, email, password, role);
        this.industry = industry;
        this.expertise = expertise;
        this.skills = skills;
        this.available = available;
    }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public String getExpertise() { return expertise; }
    public void setExpertise(String expertise) { this.expertise = expertise; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
