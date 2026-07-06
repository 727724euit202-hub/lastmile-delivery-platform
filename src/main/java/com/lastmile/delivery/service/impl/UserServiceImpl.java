package com.lastmile.delivery.service.impl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lastmile.delivery.dto.RegisterRequest;
import com.lastmile.delivery.dto.UserResponse;
import com.lastmile.delivery.entity.Role;
import com.lastmile.delivery.entity.User;
import com.lastmile.delivery.exception.EmailAlreadyExistsException;
import com.lastmile.delivery.exception.PhoneNumberAlreadyExistsException;
import com.lastmile.delivery.exception.RoleNotFoundException;
import com.lastmile.delivery.repository.RoleRepository;
import com.lastmile.delivery.repository.UserRepository;
import com.lastmile.delivery.service.UserService;
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
        userResponse.setActive(user.getActive());
        return userResponse;
    }

    
}
