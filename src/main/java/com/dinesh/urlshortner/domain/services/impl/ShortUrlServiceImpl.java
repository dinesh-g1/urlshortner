package com.dinesh.urlshortner.domain.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dinesh.urlshortner.domain.entities.ShortUrl;
import com.dinesh.urlshortner.domain.repository.ShortUrlRepository;
import com.dinesh.urlshortner.domain.services.ShortUrlService;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;

    
    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }


    @Override
    public List<ShortUrl> findPublicUrls() {
        return shortUrlRepository.findPublicUrls();
    }
    
}
