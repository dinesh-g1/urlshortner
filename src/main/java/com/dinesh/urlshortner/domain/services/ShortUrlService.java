package com.dinesh.urlshortner.domain.services;

import java.util.List;
import java.util.Optional;

import com.dinesh.urlshortner.domain.models.ShortUrlDto;
import com.dinesh.urlshortner.web.dtos.CreateShortUrlCmd;

public interface ShortUrlService {
    List<ShortUrlDto> findPublicUrls();

    ShortUrlDto createShortUrl(CreateShortUrlCmd createShortUrlCmd);

    Optional<ShortUrlDto> findUrlByShortKey(String shortUrl);
}
