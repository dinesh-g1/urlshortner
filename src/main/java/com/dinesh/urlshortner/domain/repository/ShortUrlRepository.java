package com.dinesh.urlshortner.domain.repository;

import java.util.List;
import java.util.Optional;

import com.dinesh.urlshortner.domain.models.ShortUrlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dinesh.urlshortner.domain.entities.ShortUrl;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long>{
    @Query("select su from ShortUrl su left join fetch su.createdBy where su.isPrivate = false order by su.createdAt desc")
    List<ShortUrl> findPublicUrls();

    Optional<ShortUrl> findShortUrlByShortUrl(String shortUrl);
}
