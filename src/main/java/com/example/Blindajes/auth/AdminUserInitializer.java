package com.example.Blindajes.auth;

import com.example.Blindajes.model.User;
import com.example.Blindajes.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.Blindajes.model.Role.ADMIN;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserServiceImpl userService;
    @Override
    public void run(String... args) throws Exception {
        // Tu lógica para buscar o crear el usuario admin aquí
        Optional<User> adminUserOptional = userService.findUserByUsername("admin");
        if (adminUserOptional.isEmpty()){
            RegisterRequest registerAdmin = new RegisterRequest("admin","adnmin", "admin", "admin123",ADMIN);
            authenticationService.register(registerAdmin);
        }
    }
}

