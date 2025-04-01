package com.dinesh.urlshortner.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dinesh.urlshortner.domain.entities.ShortUrl;
import com.dinesh.urlshortner.services.ShortUrlService;



@Controller
public class HomeController {
    private final ShortUrlService shortUrlService;
    
    public HomeController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ShortUrl> urls = shortUrlService.findPublicUrls();
        model.addAttribute("shortUrls", urls);
        model.addAttribute("baseUrl", "http://localhost:8080");
        return "index";
    }
    
    // @GetMapping("/about")
    // public String about() {
    //     return "about";
    // }
    
}
