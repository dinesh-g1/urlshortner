package com.dinesh.urlshortner.domain.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.dinesh.urlshortner.domain.entities.ShortUrl;
import com.dinesh.urlshortner.domain.models.ShortUrlDto;
import com.dinesh.urlshortner.domain.services.EntityManager;
import com.dinesh.urlshortner.web.dtos.CreateShortUrlCmd;
import org.apache.commons.lang3.RandomStringUtils;
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

    @Override
    public ShortUrlDto createShortUrl(CreateShortUrlCmd createShortUrlCmd) {
        String shortKey = generateRandomShortKey();

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(createShortUrlCmd.originalUrl());
        shortUrl.setCreatedAt(LocalDateTime.now());
        shortUrl.setClickCount(0L);
        shortUrl.setShortUrl(shortKey);
        shortUrl.setCreatedBy(null);
        shortUrl.setIsPrivate(false);
        shortUrl.setExpiresAt(LocalDateTime.now().plusWeeks(4));
        shortUrl = shortUrlRepository.save(shortUrl);

        return EntityManager.toShortUrlDto(shortUrl);
    }

    private String generateRandomShortKey() {
        int length = 6;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedString;
    }
}
