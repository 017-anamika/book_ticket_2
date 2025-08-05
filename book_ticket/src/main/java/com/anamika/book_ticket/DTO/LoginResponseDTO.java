package com.anamika.book_ticket.DTO;

import java.util.Set;

public class LoginResponseDTO {
    private String jwtToken;
    private String username;
    private Set<String> roles;

    // Getters
    public String getJwtToken() {
        return jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    // Setters
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    // Static Builder class (optional, if you want builder-like syntax)
    public static class Builder {
        private String jwtToken;
        private String username;
        private Set<String> roles;

        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder roles(Set<String> roles) {
            this.roles = roles;
            return this;
        }

        public LoginResponseDTO build() {
            LoginResponseDTO dto = new LoginResponseDTO();
            dto.setJwtToken(jwtToken);
            dto.setUsername(username);
            dto.setRoles(roles);
            return dto;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
