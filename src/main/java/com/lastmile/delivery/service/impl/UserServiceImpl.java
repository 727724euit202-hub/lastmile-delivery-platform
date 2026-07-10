package com.lastmile.delivery.service.impl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lastmile.delivery.dto.LoginRequest;
import com.lastmile.delivery.dto.LoginResponse;
import com.lastmile.delivery.dto.RegisterRequest;
import com.lastmile.delivery.dto.UserResponse;
import com.lastmile.delivery.entity.Role;
import com.lastmile.delivery.entity.User;
import com.lastmile.delivery.exception.EmailAlreadyExistsException;
import com.lastmile.delivery.exception.EmailDoesNotExistsException;
import com.lastmile.delivery.exception.InvalidPasswordException;
import com.lastmile.delivery.exception.PhoneNumberAlreadyExistsException;
import com.lastmile.delivery.exception.RoleNotFoundException;
import com.lastmile.delivery.repository.RoleRepository;
import com.lastmile.delivery.repository.UserRepository;
import com.lastmile.delivery.service.JWTService;
import com.lastmile.delivery.service.UserService;
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponse register(RegisterRequest request){
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already registered");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException("Phone number is already registered");
        }
        
        Role customerRole = roleRepository.findByName("CUSTOMER").orElseThrow(() -> new RoleNotFoundException("CUSTOMER role not found"));
        
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(customerRole);

        userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setRole(user.getRole().getName());
        userResponse.setIsActive(user.isActive());
        return userResponse;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new EmailDoesNotExistsException("User not found with email: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().getName());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user.getId());
        loginResponse.setName(user.getName());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setPhoneNumber(user.getPhoneNumber());
        loginResponse.setRole(user.getRole().getName());
        loginResponse.setActive(user.isActive());
        loginResponse.setMessage("Login successful");
        loginResponse.setToken(token);
        
        return loginResponse;
    }

    
}
