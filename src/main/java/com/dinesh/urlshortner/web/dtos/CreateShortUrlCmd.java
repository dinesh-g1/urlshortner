package com.dinesh.urlshortner.web.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.dinesh.urlshortner.domain.entities.ShortUrl}
 */
public record CreateShortUrlCmd(String originalUrl) implements Serializable {
}