package com.agroerp.modules.user.dto;

import com.agroerp.modules.auth.entity.User;
import java.time.LocalDateTime;

public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String role;
    private boolean active;
    private LocalDateTime createdAt;

    public UserResponse() {}

    public static UserResponse fromEntity(User u) {
        UserResponse r = new UserResponse();
        r.id        = u.getId();
        r.username  = u.getUsername();
        r.email     = u.getEmail();
        r.fullName  = u.getFullName();
        r.role      = u.getRole().name();
        r.active    = u.isActive();
        r.createdAt = u.getCreatedAt();
        return r;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }
    public String getRole() { return role; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setRole(String role) { this.role = role; }
    public void setActive(boolean active) { this.active = active; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}