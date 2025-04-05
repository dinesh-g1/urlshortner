package com.dinesh.urlshortner.domain.models;

import java.io.Serializable;

/**
 * DTO for {@link com.dinesh.urlshortner.domain.entities.User}
 */
public record UserDto(Long id, String name) implements Serializable {
  }