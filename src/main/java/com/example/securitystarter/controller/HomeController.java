package com.example.securitystarter.controller;

import com.example.securitystarter.repositories.AppUserRepository;
import com.example.securitystarter.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

        @Autowired
        AppUserRepository userRepository;

        @Autowired
        RoleRepository roleRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;


        @RequestMapping("/")
        public String showIndex()
        {
            return "index";
        }


        @RequestMapping("/grantedstudent")
        public String showUser()
        {
            return "default";
        }

        @RequestMapping("/grantedteacher")
        public String showAdmin()
        {
            return showUser();
        }
}