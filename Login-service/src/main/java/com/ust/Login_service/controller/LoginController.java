package com.ust.Login_service.controller;

//import com.ust.Login_service.model.Login;
import com.ust.Login_service.model.LoginDetails;
import com.ust.Login_service.service.LoginService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public LoginDetails register(@RequestBody LoginDetails login) {
        return loginService.register(login);
    }
}
