package com.example.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mentor; // Changed from Mentor object to String
    private String mentee; // Changed from Mentee object to String

    private LocalDateTime scheduledTime;
    private String meetingLink;
    private String status; 
    private String title;  

    public Session() {}

    public Session(String mentor, String mentee, LocalDateTime scheduledTime, String meetingLink, String status, String title) {
        this.mentor = mentor;
        this.mentee = mentee;
        this.scheduledTime = scheduledTime;
        this.meetingLink = meetingLink;
        this.status = status;
        this.title = title;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMentor() { return mentor; }
    public void setMentor(String mentor) { this.mentor = mentor; }

    public String getMentee() { return mentee; }
    public void setMentee(String mentee) { this.mentee = mentee; }

    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }

    public String getMeetingLink() { return meetingLink; }
    public void setMeetingLink(String meetingLink) { this.meetingLink = meetingLink; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTitle() { return title; }  
    public void setTitle(String title) { this.title = title; }

    public String getSession() {  
        return "Session Title: " + title + ", Mentor: " + mentor + ", Mentee: " + mentee;
    }
}
