/*package com.micro.gatewayserver.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;*/

/*public class JWTConverter implements Converter<Jwt,Collection<GrantedAuthority>> {


    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
      /*  Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");

        if (realmAccess == null || realmAccess.isEmpty()) {
            return new ArrayList<>();
        }*/

     /*   Collection<GrantedAuthority> authorities = ((List<String>) source.getClaims().get("roles")).stream()
                .map(roleName->"ROLE_"+roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return authorities;
    }
}*/
