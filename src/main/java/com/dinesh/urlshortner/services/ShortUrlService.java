package com.dinesh.urlshortner.services;

import java.util.List;

import com.dinesh.urlshortner.domain.entities.ShortUrl;

public interface ShortUrlService {
    List<ShortUrl> findPublicUrls();
}
