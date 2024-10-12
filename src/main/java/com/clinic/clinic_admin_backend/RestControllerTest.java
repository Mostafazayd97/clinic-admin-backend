package com.clinic.clinic_admin_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerTest {
    
    @GetMapping("/init")
    public String getInit(){
        return "init";
    }
}
