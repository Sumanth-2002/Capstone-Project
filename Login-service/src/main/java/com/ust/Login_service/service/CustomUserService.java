package com.ust.Login_service.service;

//import com.ust.Login_service.model.Login;
import com.ust.Login_service.model.LoginDetails;
import com.ust.Login_service.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        LoginDetails login =  loginRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userId));

        return new User(login.getUserId(), login.getPassword(), Collections.emptyList());
    }
}
