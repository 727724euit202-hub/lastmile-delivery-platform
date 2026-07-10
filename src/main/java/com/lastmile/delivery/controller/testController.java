package com.lastmile.delivery.controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class testController {
    public String response = "Successful Login  After JWTAuthentication";

    @GetMapping("/test")
public String test(Authentication authentication) {

    System.out.println(authentication.getName());

    System.out.println(authentication.getAuthorities());

    System.out.println(authentication.isAuthenticated());

    return "Done";
}
}
