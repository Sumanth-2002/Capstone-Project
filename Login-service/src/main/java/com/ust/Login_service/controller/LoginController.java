package com.ust.Login_service.controller;

import com.ust.Login_service.model.Login;
import com.ust.Login_service.service.LoginService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/register")
    public Login register(@RequestBody Login login) {
        return loginService.register(login);
    }
}
