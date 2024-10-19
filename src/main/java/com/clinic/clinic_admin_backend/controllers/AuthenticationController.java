package com.clinic.clinic_admin_backend.controllers;

import com.clinic.clinic_admin_backend.DTO.LoginUserDTO;
import com.clinic.clinic_admin_backend.DTO.UserDTO;
import com.clinic.clinic_admin_backend.models.User;
import com.clinic.clinic_admin_backend.responses.LoginResponse;
import com.clinic.clinic_admin_backend.services.AuthenticationService;
import com.clinic.clinic_admin_backend.services.JwtService;
import com.clinic.clinic_admin_backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public User SignUp(@RequestBody UserDTO user) {
        if (user == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        return authenticationService.SignUp(user);
    }
    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDTO user) {
        if (user == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        User authenticatedUser = authenticationService.authenticate(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtService.generateToken(authenticatedUser));
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
