package com.lastmile.delivery.service;
import com.lastmile.delivery.dto.RegisterRequest;
import com.lastmile.delivery.dto.UserResponse;
public interface UserService{
    UserResponse register(RegisterRequest request);
}
