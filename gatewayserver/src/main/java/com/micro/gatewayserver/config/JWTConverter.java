package com.micro.gatewayserver.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JWTConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        System.out.println("JWTConverter invoked");
        System.out.println("JWT Claims: " + jwt.getClaims());

        // Extract roles from the "roles" claim
        List<String> roles = (List<String>) jwt.getClaims().get("roles");
        System.out.println("Roles from JWT: " + roles);

        if (roles == null || roles.isEmpty()) {
            System.out.println("No roles found in JWT");
            return List.of(); // Return an empty list if no roles are found
        }

        // Map roles to GrantedAuthority objects
        Collection<GrantedAuthority> authorities = roles.stream()
                .map(role -> "ROLE_" + role) // Add ROLE_ prefix
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        System.out.println("Granted Authorities: " + authorities);
        return authorities;
    }
}
