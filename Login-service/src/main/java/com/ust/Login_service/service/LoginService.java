package com.ust.Login_service.service;

import com.ust.Login_service.model.Login;
import com.ust.Login_service.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Login register(Login login) {
        return loginRepository.save(login);
    }
}
