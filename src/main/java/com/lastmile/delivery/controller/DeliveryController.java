package com.lastmile.delivery.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    
    @GetMapping()
    public String Result(Authentication a){
        return "You have entered this endpoint as you are a "+a.getAuthorities();
    }
}