package com.micro.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;


@Configuration
@EnableWebFluxSecurity
public class securityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/school/auth2/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/school/students/add").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/school/students/**").hasAnyRole("ADMIN","STUDENT")
                        .pathMatchers(HttpMethod.DELETE, "/school/students/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/school/teachers/add").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/school/teachers/**").hasAnyRole("ADMIN","TEACHER")
                        .pathMatchers(HttpMethod.DELETE, "/school/teachers/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/school/modules/add").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/school/modules/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/school/modules/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/school/enrollments/**").hasRole("ADMIN")

                        // TEACHER role can access specific endpoints
                        .pathMatchers(HttpMethod.GET, "/school/teachers/**").hasAnyRole("TEACHER", "ADMIN")
                        .pathMatchers(HttpMethod.GET, "/school/modules/**").hasAnyRole("TEACHER", "ADMIN","STUDENT")
                        .pathMatchers(HttpMethod.GET, "/school/enrollments/**").hasAnyRole("TEACHER", "ADMIN","STUDENT")

                        // STUDENT role can access specific endpoints
                        .pathMatchers(HttpMethod.GET, "/school/students/**").hasAnyRole("STUDENT", "ADMIN")
                        .anyExchange().authenticated()

                )
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor()))
                )
                .csrf(csrfSpec -> csrfSpec.disable()); // Disable CSRF for simplicity

        return serverHttpSecurity.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new JWTConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
