package com.agroerp.modules.auth.dto;

public class LoginResponse {

    private String token;
    private String tokenType;
    private Long userId;
    private String username;
    private String email;
    private String fullName;
    private String role;
    private long expiresIn;

    public LoginResponse() {}

    // Getters
    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }
    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }
    public String getRole() { return role; }
    public long getExpiresIn() { return expiresIn; }

    // Setters
    public void setToken(String token) { this.token = token; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setRole(String role) { this.role = role; }
    public void setExpiresIn(long expiresIn) { this.expiresIn = expiresIn; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String token;
        private String tokenType = "Bearer";
        private Long userId;
        private String username;
        private String email;
        private String fullName;
        private String role;
        private long expiresIn;

        public Builder token(String token) { this.token = token; return this; }
        public Builder tokenType(String tokenType) { this.tokenType = tokenType; return this; }
        public Builder userId(Long userId) { this.userId = userId; return this; }
        public Builder username(String username) { this.username = username; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder fullName(String fullName) { this.fullName = fullName; return this; }
        public Builder role(String role) { this.role = role; return this; }
        public Builder expiresIn(long expiresIn) { this.expiresIn = expiresIn; return this; }

        public LoginResponse build() {
            LoginResponse r = new LoginResponse();
            r.token = this.token;
            r.tokenType = this.tokenType;
            r.userId = this.userId;
            r.username = this.username;
            r.email = this.email;
            r.fullName = this.fullName;
            r.role = this.role;
            r.expiresIn = this.expiresIn;
            return r;
        }
    }
}