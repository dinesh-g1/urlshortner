package com.dinesh.urlshortner.domain.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.dinesh.urlshortner.domain.models.ShortUrlDto;
import com.dinesh.urlshortner.domain.services.EntityManager;
import org.springframework.stereotype.Service;

import com.dinesh.urlshortner.domain.repository.ShortUrlRepository;
import com.dinesh.urlshortner.domain.services.ShortUrlService;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;

    
    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }


    @Override
    public List<ShortUrlDto> findPublicUrls() {
        return shortUrlRepository.findPublicUrls()
                .stream().map(EntityManager::toShortUrlDto).collect(Collectors.toList());
    }
    
}
