package com.ust.Login_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Login {
    @Id
    private String UserId;
    private String Password;
    private String role;

    public Login(String userId, String password, String role) {
        UserId = userId;
        Password = password;
        this.role = role;
    }

    public Login() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
