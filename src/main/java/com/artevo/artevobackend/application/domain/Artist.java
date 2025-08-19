package com.artevo.artevobackend.application.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public record Artist(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        String name,
        String username,
        String password
) {}
