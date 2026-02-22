package com.example.demo.dto;

import java.time.LocalDateTime;

public record SightingDto(
        String id,
        LocalDateTime date,
        String location,
        String species,
        String latinName
) {}