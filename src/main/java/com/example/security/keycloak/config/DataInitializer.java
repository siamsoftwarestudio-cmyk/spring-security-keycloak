package com.example.security.keycloak.config;

import com.example.security.keycloak.entity.Role;
import com.example.security.keycloak.entity.User;
import com.example.security.keycloak.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                userRepository.save(new User("admin", Role.ADMIN));
            }
            if (!userRepository.existsByUsername("user")) {
                userRepository.save(new User("user", Role.USER));
            }
        };
    }
}
