package com.artevo.artevobackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF aus
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // alles unter /api/** frei
                        .anyRequest().permitAll() // auch Rest erstmal frei
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .formLogin(login -> login.disable()) // Login-Formular deaktivieren
                .httpBasic(basic -> basic.disable()) // Basic Auth deaktivieren
                .logout(logout -> logout.disable()) // Logout deaktivieren
                .sessionManagement(session -> session.disable()); // Session Management komplett deaktivieren

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
}
