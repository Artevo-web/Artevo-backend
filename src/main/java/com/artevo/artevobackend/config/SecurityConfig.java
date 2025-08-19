package com.artevo.artevobackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF deaktivieren für API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // Public Endpoints
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> httpBasic.disable()); // Default Login deaktivieren

        return http.build();
    }
}
