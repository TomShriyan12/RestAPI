package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "mentees")
public class Mentee extends User {
    private String careerGoal;
    
    @ElementCollection
    private List<String> interests;

    public Mentee() {}

    public Mentee(String name, String email, String password, String role, String careerGoal, List<String> interests) {
        super(name, email, password, role);
        this.careerGoal = careerGoal;
        this.interests = interests;
    }

    public String getCareerGoal() { return careerGoal; }
    public void setCareerGoal(String careerGoal) { this.careerGoal = careerGoal; }

    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }
}
