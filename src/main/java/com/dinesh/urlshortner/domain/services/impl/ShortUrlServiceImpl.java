package com.dinesh.urlshortner.domain.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dinesh.urlshortner.domain.entities.ShortUrl;
import com.dinesh.urlshortner.domain.exceptions.ShortURLNotFoundException;
import com.dinesh.urlshortner.domain.models.ShortUrlDto;
import com.dinesh.urlshortner.domain.services.EntityManager;
import com.dinesh.urlshortner.web.dtos.CreateShortUrlCmd;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.dinesh.urlshortner.domain.repository.ShortUrlRepository;
import com.dinesh.urlshortner.domain.services.ShortUrlService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
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
    @Transactional
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

    @Override
    @Transactional
    public Optional<ShortUrlDto> findUrlByShortKey(String shortUrl) {
        Optional<ShortUrl> originalUrl = shortUrlRepository.findShortUrlByShortUrl(shortUrl);
        if (originalUrl.isEmpty()) {
            throw new ShortURLNotFoundException(shortUrl);
        }
        originalUrl.get().setClickCount(originalUrl.get().getClickCount()+1);
        shortUrlRepository.save(originalUrl.get());

        return Optional.of(EntityManager.toShortUrlDto(originalUrl.get()));
    }

    private String generateRandomShortKey() {
        int length = 6;
        boolean useLetters = true;
        boolean useNumbers = false;

        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
