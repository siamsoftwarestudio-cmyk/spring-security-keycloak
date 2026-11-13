package com.example.security.keycloak.service;

import com.example.security.keycloak.entity.Role;
import com.example.security.keycloak.entity.User;
import com.example.security.keycloak.repository.UserRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User findOrCreate(String username, Collection<? extends GrantedAuthority> authorities) {
        Role role = authorities.stream().anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()))
                ? Role.ADMIN
                : Role.USER;

        return userRepository.findByUsername(username)
                .map(existing -> {
                    if (existing.getRole() != role) {
                        existing.setRole(role);
                    }
                    return existing;
                })
                .orElseGet(() -> userRepository.save(new User(username, role)));
    }
}
