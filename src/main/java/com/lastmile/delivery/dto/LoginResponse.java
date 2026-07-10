package com.lastmile.delivery.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String role;
    private boolean active;
    private String message;
    private String token;
}
