package com.dinesh.urlshortner.domain.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app")
public record ApplicationProperties(
        @NotBlank
                @DefaultValue("http://localhost:8080")
        String baseUrl,
        @Min(1)
        @Max(365)
        @DefaultValue("30")
        int expiryInDays
) {}
