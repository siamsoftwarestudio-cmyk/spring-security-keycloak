package com.example.security.keycloak.repository;

import com.example.security.keycloak.entity.Order;
import com.example.security.keycloak.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByOwnerOrderByCreatedAtDesc(User owner);
}
