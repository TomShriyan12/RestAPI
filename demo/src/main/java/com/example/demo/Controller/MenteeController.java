package com.example.demo.Controller;

import com.example.demo.Entity.Mentee;
import com.example.demo.Service.MenteeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mentees")
@Tag(name = "Mentee API", description = "Endpoints for managing mentees") // Tag for grouping APIs
public class MenteeController {

    @Autowired
    private MenteeService menteeService;

    @Operation(summary = "Get all mentees", description = "Retrieves a list of all mentees")
    @GetMapping
    public List<Mentee> getAllMentees() {
        return menteeService.getAllMentees();
    }

    @Operation(summary = "Get mentee by ID", description = "Retrieve a specific mentee using their ID")
    @GetMapping("/{id}")
    public ResponseEntity<Mentee> getMenteeById(
            @Parameter(description = "ID of the mentee to be retrieved") @PathVariable Long id) {
        Optional<Mentee> mentee = menteeService.getMenteeById(id);
        return mentee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new mentee", description = "Adds a new mentee to the system")
    @PostMapping
    public Mentee createMentee(@RequestBody Mentee mentee) {
        return menteeService.createMentee(mentee);
    }

    @Operation(summary = "Delete a mentee", description = "Removes a mentee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentee(
            @Parameter(description = "ID of the mentee to be deleted") @PathVariable Long id) {
        menteeService.deleteMentee(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a mentee", description = "Updates details of an existing mentee")
    @PutMapping("/{id}")
    public ResponseEntity<Mentee> updateMentee(
            @Parameter(description = "ID of the mentee to be updated") @PathVariable Long id,
            @RequestBody Mentee mentee) {
        Optional<Mentee> updatedMentee = menteeService.updateMentee(id, mentee);
        return updatedMentee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get paginated mentees", description = "Retrieve mentees with pagination and sorting")
    @GetMapping("/paginated")
    public Page<Mentee> getMenteesPaginated(
            @Parameter(description = "Page number (default: 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size (default: 10)") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sorting field (default: name)") @RequestParam(defaultValue = "name") String sortBy,
            @Parameter(description = "Sorting direction (asc/desc, default: asc)") @RequestParam(defaultValue = "asc") String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return menteeService.getAllMenteesPaginated(pageable);
    }
}
