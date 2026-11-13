package com.example.security.keycloak.dto;

import com.example.security.keycloak.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderStatusRequest(
        @NotNull OrderStatus status
) {
}
