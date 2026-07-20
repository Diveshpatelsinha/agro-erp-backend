package com.agroerp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {

    private int status;
    private String error;
    private String message;
    private String path;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private Map<String, String> validationErrors;

    public ErrorResponse() {}

    // Getters
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public Map<String, String> getValidationErrors() { return validationErrors; }

    // Setters
    public void setStatus(int status) { this.status = status; }
    public void setError(String error) { this.error = error; }
    public void setMessage(String message) { this.message = message; }
    public void setPath(String path) { this.path = path; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setValidationErrors(Map<String, String> validationErrors) { this.validationErrors = validationErrors; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private int status;
        private String error;
        private String message;
        private String path;
        private LocalDateTime timestamp;
        private Map<String, String> validationErrors;

        public Builder status(int status) { this.status = status; return this; }
        public Builder error(String error) { this.error = error; return this; }
        public Builder message(String message) { this.message = message; return this; }
        public Builder path(String path) { this.path = path; return this; }
        public Builder timestamp(LocalDateTime timestamp) { this.timestamp = timestamp; return this; }
        public Builder validationErrors(Map<String, String> validationErrors) { this.validationErrors = validationErrors; return this; }

        public ErrorResponse build() {
            ErrorResponse r = new ErrorResponse();
            r.status = this.status;
            r.error = this.error;
            r.message = this.message;
            r.path = this.path;
            r.timestamp = this.timestamp;
            r.validationErrors = this.validationErrors;
            return r;
        }
    }
}