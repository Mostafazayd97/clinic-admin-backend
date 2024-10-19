package com.clinic.clinic_admin_backend.services;

import com.clinic.clinic_admin_backend.DTO.LoginUserDTO;
import com.clinic.clinic_admin_backend.DTO.UserDTO;
import com.clinic.clinic_admin_backend.models.User;
import com.clinic.clinic_admin_backend.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public User SignUp(UserDTO user) {
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setVerificationCode(generateVerificationCode());
        newUser.setEnabled(true);
        newUser.setVerificationCodeExpiresAt(LocalDateTime.now().plusDays(1));
        return userRepository.save(newUser);
    }

    public User authenticate(LoginUserDTO loginUserDto) {

        User user = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(() -> new RuntimeException("User not found with email: " + loginUserDto.getEmail()));
//        if(!user.isEnabled()){
//            throw new RuntimeException("User not Verfied");
//        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));
        return user;
    }

    public String generateVerificationCode(){
        String random = String.valueOf((int) (Math.random() * 900000) + 100000);
        return random;
    }

}
