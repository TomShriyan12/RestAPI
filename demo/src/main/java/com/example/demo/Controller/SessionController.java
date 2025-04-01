package com.example.demo.Controller;

import com.example.demo.Entity.Session;
import com.example.demo.Service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessions")
@Tag(name = "Session API", description = "Endpoints for managing mentorship sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @Operation(summary = "Get all sessions", description = "Retrieve a list of all mentorship sessions")
    @GetMapping
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @Operation(summary = "Get paginated sessions", description = "Retrieve sessions with pagination and sorting")
    @GetMapping("/paginated")
    public Page<Session> getSessionsPaginated(Pageable pageable) {
        return sessionService.getSessionsPaginated(pageable);
    }

    @Operation(summary = "Get a session by ID", description = "Retrieve a specific mentorship session by ID")
    @GetMapping("/{id}")
    public Optional<Session> getSessionById(
            @Parameter(description = "ID of the session") @PathVariable Long id) {
        return sessionService.getSessionById(id);
    }

    @Operation(summary = "Create a new session", description = "Add a new mentorship session")
    @PostMapping
    public Session createSession(@RequestBody Session session) {
        return sessionService.createSession(session);
    }

    @Operation(summary = "Delete a session", description = "Remove a session by ID")
    @DeleteMapping("/{id}")
    public String deleteSession(
            @Parameter(description = "ID of the session to delete") @PathVariable Long id) {
        sessionService.deleteSession(id);
        return "deleted successfully";
    }
}
