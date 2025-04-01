package com.dinesh.urlshortner.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dinesh.urlshortner.domain.entities.ShortUrl;
import com.dinesh.urlshortner.repository.ShortUrlRepository;
import com.dinesh.urlshortner.services.ShortUrlService;

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
