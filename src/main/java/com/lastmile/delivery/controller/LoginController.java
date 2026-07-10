package com.lastmile.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lastmile.delivery.dto.LoginRequest;
import com.lastmile.delivery.dto.LoginResponse;
import com.lastmile.delivery.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    
}
