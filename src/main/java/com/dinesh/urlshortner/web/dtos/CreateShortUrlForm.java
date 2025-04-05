package com.dinesh.urlshortner.web.dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public record CreateShortUrlForm (
        @NotBlank(message = "Original URL is required")
        String originalUrl
) {}
