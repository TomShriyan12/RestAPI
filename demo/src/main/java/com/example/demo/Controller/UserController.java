package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
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
@RequestMapping("/users")
@Tag(name = "User API", description = "Endpoints for managing users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get paginated users", description = "Retrieve users with pagination and sorting")
    @GetMapping("/paginated")
    public Page<User> getUsersPaginated(Pageable pageable) {
        return userService.getUsersPaginated(pageable);
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a specific user by ID")
    @GetMapping("/{id}")
    public Optional<User> getUserById(@Parameter(description = "User ID") @PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Get users by role", description = "Retrieve all users with a specific role")
    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@Parameter(description = "Role of the user") @PathVariable String role) {
        return userService.getUsersByRole(role);
    }

    @Operation(summary = "Search users by name", description = "Find users based on their name")
    @GetMapping("/search")
    public List<User> searchUsersByName(@RequestParam String name) {
        return userService.searchUsersByName(name);
    }

    @Operation(summary = "Get user count by role", description = "Retrieve the count of users for a given role")
    @GetMapping("/count/{role}")
    public Long getUserCountByRole(@Parameter(description = "Role of the users") @PathVariable String role) {
        return userService.getUserCountByRole(role);
    }

    @Operation(summary = "Find user by email", description = "Retrieve a user by email address")
    @GetMapping("/find")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.findUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Update user details", description = "Modify an existing user's details")
    @PutMapping("/{id}")
    public Optional<User> updateUser(
            @Parameter(description = "User ID") @PathVariable Long id, 
            @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @Operation(summary = "Delete a user", description = "Remove a user by ID")
    @DeleteMapping("/{id}")
    public String deleteUser(@Parameter(description = "User ID") @PathVariable Long id) {
        userService.deleteUser(id);
        return "Deleted successfully";
    }
}
