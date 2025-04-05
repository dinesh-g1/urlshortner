package com.dinesh.urlshortner.domain.services;

import java.util.List;

import com.dinesh.urlshortner.domain.models.ShortUrlDto;

public interface ShortUrlService {
    List<ShortUrlDto> findPublicUrls();
}
