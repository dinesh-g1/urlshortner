package com.dinesh.urlshortner.domain.services;

import com.dinesh.urlshortner.domain.entities.ShortUrl;
import com.dinesh.urlshortner.domain.entities.User;
import com.dinesh.urlshortner.domain.models.ShortUrlDto;
import com.dinesh.urlshortner.domain.models.UserDto;
import org.springframework.stereotype.Component;

@Component
public class EntityManager {
    public static ShortUrlDto toShortUrlDto(ShortUrl shortUrl) {
        UserDto userDto = null;
        if (shortUrl.getCreatedBy() != null) {
            userDto = toUserDto(shortUrl.getCreatedBy());
        }
        return new ShortUrlDto(
                shortUrl.getId(),
                shortUrl.getOriginalUrl(),
                shortUrl.getShortUrl(),
                userDto,
                shortUrl.getIsPrivate(),
                shortUrl.getCreatedAt(),
                shortUrl.getExpiresAt(),
                shortUrl.getClickCount()
        );
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}
