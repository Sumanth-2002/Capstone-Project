package com.ust.Company_Service.model;

public class LoginDto {
    private String userId;
    private String password;
    private String role="Company";

    public LoginDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public LoginDto() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
}
