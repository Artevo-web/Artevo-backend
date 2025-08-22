package com.artevo.artevobackend.adapter.in;

import com.artevo.artevobackend.application.domain.Design;

import java.util.Base64;

public record DesignDto(
        Long id,
        String fileName,
        String base64Data
) {
    public static DesignDto fromEntity(Design design) {
        return new DesignDto(
                design.getId(),
                design.getFileName(),
                Base64.getEncoder().encodeToString(design.getData())
        );
    }
}
