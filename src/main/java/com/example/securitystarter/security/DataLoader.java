package com.example.securitystarter.security;

import com.example.securitystarter.entity.AppUser;
import com.example.securitystarter.entity.Role;
import com.example.securitystarter.repositories.AppUserRepository;
import com.example.securitystarter.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {

        Role aRole = new Role();
        aRole.setRole("TEACHER");
        roleRepository.save(aRole);

        aRole = new Role();
        aRole.setRole("STUDENT");
        roleRepository.save(aRole);

        AppUser user = new AppUser();
        user.setPassword("password");
        user.setUsername("student");
        user.addRole(roleRepository.findByRole("STUDENT"));
        userRepository.save(user);

        user = new AppUser();
        user.setUsername("teacher");
        user.setPassword("password");
        user.addRole(roleRepository.findByRole("TEACHER"));
        userRepository.save(user);
    }

}
