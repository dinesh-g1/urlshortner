package com.dinesh.urlshortner.domain.models;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.dinesh.urlshortner.domain.entities.ShortUrl}
 */
public record ShortUrlDto(Long id, String originalUrl, String shortUrl, UserDto createdBy, Boolean isPrivate, LocalDateTime createdAt, LocalDateTime expiresAt, Long clickCount) implements Serializable {
  }