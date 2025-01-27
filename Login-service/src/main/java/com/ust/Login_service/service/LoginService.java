package com.ust.Login_service.service;

import com.ust.Login_service.dto.AuthenticationRequest;
//import com.ust.Login_service.model.Login;
import com.ust.Login_service.model.LoginDetails;
import com.ust.Login_service.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public LoginDetails register(LoginDetails login) {
        System.out.println(login);
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        return loginRepository.save(login);
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
