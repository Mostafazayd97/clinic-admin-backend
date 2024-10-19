package com.clinic.clinic_admin_backend.responses;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private Long ExpiresIn;
}
