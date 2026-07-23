package com.agroerp.modules.user.controller;

import com.agroerp.modules.auth.dto.RegisterRequest;
import com.agroerp.modules.auth.entity.User;
import com.agroerp.modules.auth.service.AuthService;
import com.agroerp.modules.user.dto.ChangePasswordRequest;
import com.agroerp.modules.user.dto.UpdateUserRequest;
import com.agroerp.modules.user.dto.UserResponse;
import com.agroerp.modules.user.service.UserManagementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN')")
public class UserManagementController {

    private final UserManagementService userManagementService;
    private final AuthService           authService;

    public UserManagementController(
            UserManagementService userManagementService,
            AuthService authService) {
        this.userManagementService = userManagementService;
        this.authService           = authService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(
                userManagementService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                userManagementService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> create(
            @Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "User created successfully",
                        "username", user.getUsername()
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(
                userManagementService.update(id, request));
    }

    @PatchMapping("/{id}/toggle-active")
    public ResponseEntity<UserResponse> toggleActive(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                userManagementService.toggleActive(id));
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<Map<String, String>> changePassword(
            @PathVariable Long id,
            @Valid @RequestBody ChangePasswordRequest request) {
        userManagementService.changePassword(id, request);
        return ResponseEntity.ok(Map.of(
                "message", "Password changed successfully"
        ));
    }
}