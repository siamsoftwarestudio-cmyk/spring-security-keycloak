package com.example.security.keycloak.dto;

import com.example.security.keycloak.entity.OrderStatus;
import java.math.BigDecimal;
import java.time.Instant;

public record OrderResponse(
        Long id,
        String product,
        Integer quantity,
        BigDecimal price,
        OrderStatus status,
        Instant createdAt,
        String ownerUsername
) {
}
