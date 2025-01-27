package com.ust.Login_service.dto;

import jakarta.persistence.Id;

public class AuthenticationRequest {
    @Id
    private String UserId;
    private String Password;

    public AuthenticationRequest(String userId, String password) {
        UserId = userId;
        Password = password;
    }

    public AuthenticationRequest() {
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
}
