package com.dinesh.urlshortner.domain.exceptions;

public class ShortURLNotFoundException extends RuntimeException {
    public ShortURLNotFoundException(String shortURL) {
        super("Could not find shortURL: " + shortURL);
    }
}
