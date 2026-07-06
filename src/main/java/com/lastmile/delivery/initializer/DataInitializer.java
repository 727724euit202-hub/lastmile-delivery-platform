package com.lastmile.delivery.initializer;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lastmile.delivery.entity.Role;
import com.lastmile.delivery.repository.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner{
    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> roles = List.of("ADMIN", "DISPATCHER", "DELIVERY_AGENT", "CUSTOMER");

        for(String roleName : roles) {
            if(!roleRepository.existsByName(roleName)) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        }
    }
}
