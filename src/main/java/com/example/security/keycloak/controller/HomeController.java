package com.example.security.keycloak.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "login", "/oauth2/authorization/keycloak",
                "orders", "/api/orders"
        );
    }
}
