package com.lastmile.delivery.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lastmile.delivery.dto.RegisterRequest;
import com.lastmile.delivery.dto.UserResponse;
import com.lastmile.delivery.entity.User;
import com.lastmile.delivery.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterRequest request) {
        User registeredUser = userService.register(request);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(registeredUser.getId());
        userResponse.setName(registeredUser.getName());
        userResponse.setEmail(registeredUser.getEmail());
        userResponse.setPhoneNumber(registeredUser.getPhoneNumber());
        userResponse.setRole(registeredUser.getRole().getName());
        userResponse.setActive(registeredUser.getActive());
        return ResponseEntity.ok(userResponse);
    }

   
}
