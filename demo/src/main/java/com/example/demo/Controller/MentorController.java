package com.example.demo.Controller;

import com.example.demo.Entity.Mentor;
import com.example.demo.Service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mentors")
@Tag(name = "Mentor API", description = "Endpoints for managing mentors")
public class MentorController {
    @Autowired
    private MentorService mentorService;

    @Operation(summary = "Get all mentors", description = "Retrieves a list of all mentors")
    @GetMapping
    public List<Mentor> getAllMentors() {
        return mentorService.getAllMentors();
    }

    @Operation(summary = "Get paginated mentors", description = "Retrieve mentors with pagination and sorting")
    @GetMapping("/paginated")
    public Page<Mentor> getAllMentorsPaginated(Pageable pageable) {
        return mentorService.getAllMentorsPaginated(pageable);
    }

    @Operation(summary = "Get mentor by ID", description = "Retrieve a specific mentor using their ID")
    @GetMapping("/{id}")
    public ResponseEntity<Mentor> getMentorById(
            @Parameter(description = "ID of the mentor to be retrieved") @PathVariable Long id) {
        Optional<Mentor> mentor = mentorService.getMentorById(id);
        return mentor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new mentor", description = "Adds a new mentor to the system")
    @PostMapping
    public Mentor createMentor(@RequestBody Mentor mentor) {
        return mentorService.createMentor(mentor);
    }

    @Operation(summary = "Delete a mentor", description = "Removes a mentor by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentor(
            @Parameter(description = "ID of the mentor to be deleted") @PathVariable Long id) {
        mentorService.deleteMentor(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a mentor", description = "Updates details of an existing mentor")
    @PutMapping("/{id}")
    public ResponseEntity<Mentor> updateMentor(
            @Parameter(description = "ID of the mentor to be updated") @PathVariable Long id,
            @RequestBody Mentor mentor) {
        Optional<Mentor> updatedMentor = mentorService.updateMentor(id, mentor);
        return updatedMentor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get paginated mentors by industry", description = "Retrieve mentors by industry with pagination")
    @GetMapping("/industry/{industry}/paginated")
    public Page<Mentor> getMentorsByIndustryPaginated(
            @Parameter(description = "Industry to filter mentors by") @PathVariable String industry,
            Pageable pageable) {
        return mentorService.getMentorsByIndustryPaginated(industry, pageable);
    }
}
