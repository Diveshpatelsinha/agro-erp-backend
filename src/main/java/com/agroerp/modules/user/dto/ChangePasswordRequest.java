package com.agroerp.modules.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangePasswordRequest {

    @NotBlank(message = "New password is required")
    @Size(min = 6, max = 100,
            message = "Password must be at least 6 characters")
    private String newPassword;

    public ChangePasswordRequest() {}

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}