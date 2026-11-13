package com.example.security.keycloak.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public final class KeycloakRoleConverter {

    private KeycloakRoleConverter() {
    }

    public static Collection<GrantedAuthority> fromClaims(Map<String, Object> claims) {
        Set<String> roles = new HashSet<>();

        Object realmAccess = claims.get("realm_access");
        if (realmAccess instanceof Map<?, ?> realmAccessMap) {
            Object nestedRoles = realmAccessMap.get("roles");
            addRoles(roles, nestedRoles);
        }

        addRoles(roles, claims.get("realm_access.roles"));
        addRoles(roles, claims.get("roles"));

        return roles.stream()
                .filter(role -> "USER".equals(role) || "ADMIN".equals(role))
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast)
                .collect(Collectors.toList());
    }

    public static Collection<GrantedAuthority> fromJwt(Jwt jwt) {
        return fromClaims(jwt.getClaims());
    }

    private static void addRoles(Set<String> roles, Object rolesObject) {
        if (rolesObject instanceof Collection<?> collection) {
            collection.stream()
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .forEach(roles::add);
        }
    }
}
