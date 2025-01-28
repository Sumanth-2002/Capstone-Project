package com.ust.Login_service.controller;

//import com.ust.Login_service.model.Login;
import com.ust.Login_service.dto.AuthenticationRequest;
import com.ust.Login_service.model.LoginDetails;
import com.ust.Login_service.service.LoginService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public LoginDetails register(@RequestBody LoginDetails login) {
        return loginService.register(login);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUserId(), authenticationRequest.getPassword())
        );

        // Fetch user details from the database
//        UserInfo userInfo = userRepository.findByEmail(authenticationRequest.getEmail())
//                .orElseThrow(() -> new Exception("User not found with email: " + authenticationRequest.getEmail()));
//
//        // Generate JWT with role, region, or storeId based on role
//        final String jwt = jwtUtil.generateToken(
//                userInfo.getEmail(),
//                userInfo.getRoles().name(),
//                userInfo.getRegion(),
//                userInfo.getStoreId()
//        );

        return ResponseEntity.ok("Login Service");
    }
}
