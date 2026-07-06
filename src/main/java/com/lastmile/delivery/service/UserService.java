package com.lastmile.delivery.service;
import com.lastmile.delivery.dto.RegisterRequest;
import com.lastmile.delivery.entity.User;
public interface UserService{
    User register(RegisterRequest request);
}
