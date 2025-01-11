package com.imad.springAuthServer.service;


import com.imad.springAuthServer.Repo.clientRepo;
import com.imad.springAuthServer.entities.client;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final clientRepo clientRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        client c = this.clientRepo.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("can not found the userName"));
        List<GrantedAuthority> l = c.getAuthoroties().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());


        return User.builder()
                .username(c.getUserName())
                .password(c.getPassword())
                .authorities(l)
                .build();


    }
}
