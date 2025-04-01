package com.dinesh.urlshortner.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dinesh.urlshortner.domain.entities.ShortUrl;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long>{
    @Query("select su from ShortUrl su where su.isPrivate = false order by su.createdAt desc")
    public List<ShortUrl> findPublicUrls();
}
