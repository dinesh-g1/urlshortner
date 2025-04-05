package com.dinesh.urlshortner.web.controllers;

import java.util.List;
import java.util.Optional;

import com.dinesh.urlshortner.domain.config.ApplicationProperties;
import com.dinesh.urlshortner.domain.exceptions.ShortURLNotFoundException;
import com.dinesh.urlshortner.domain.models.ShortUrlDto;
import com.dinesh.urlshortner.web.dtos.CreateShortUrlCmd;
import com.dinesh.urlshortner.web.dtos.CreateShortUrlForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.dinesh.urlshortner.domain.services.ShortUrlService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomeController {
    private final ShortUrlService shortUrlService;
    private final ApplicationProperties appProperties;
    
    public HomeController(ShortUrlService shortUrlService, ApplicationProperties appProperties) {
        this.shortUrlService = shortUrlService;
        this.appProperties = appProperties;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ShortUrlDto> urls = shortUrlService.findPublicUrls();
        model.addAttribute("shortUrls", urls);
        model.addAttribute("baseUrl", appProperties.baseUrl());
        model.addAttribute("createShortUrlForm", new CreateShortUrlForm(""));
        return "index";
    }

    @PostMapping("/shorturls")
    String createShortUrl(@ModelAttribute("createShortUrlForm") @Valid CreateShortUrlForm form,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        if (bindingResult.hasErrors()) {
            List<ShortUrlDto> urls = shortUrlService.findPublicUrls();
            model.addAttribute("shortUrls", urls);
            model.addAttribute("baseUrl", appProperties.baseUrl());
            model.addAttribute("createShortUrlForm", new CreateShortUrlForm(""));
            return "index";
        }
        try {
            CreateShortUrlCmd createShortUrlCmd = new CreateShortUrlCmd(form.originalUrl());
            ShortUrlDto shortUrlDto = shortUrlService.createShortUrl(createShortUrlCmd);
            redirectAttributes.addFlashAttribute("successMessage", "Created shortUrl:"+shortUrlDto.shortUrl());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/s/{shortUrl}")
    public String redirectToOriginalUrl(@PathVariable String shortUrl) {
        Optional<ShortUrlDto> shortUrlDto = shortUrlService.findUrlByShortKey(shortUrl);
        if (shortUrlDto.isEmpty()) {
            throw new ShortURLNotFoundException(shortUrl);
        }
        return "redirect:" + shortUrlDto.get().originalUrl();
    }
}
