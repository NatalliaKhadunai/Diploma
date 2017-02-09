package com.minsk24.controller;

import com.minsk24.bean.*;
import com.minsk24.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private AccountService accountService;

    @RequestMapping (value = "/articles", method = RequestMethod.GET)
    public Iterable<Article> getArticles() {
        return articleService.getArticles();
    }

    @RequestMapping (value = "/addArticle", method = RequestMethod.POST)
    public void addArticle(Principal principal,
                           @RequestParam(value = "mainTitle") String mainTitle,
                           @RequestParam(value = "shortTitle") String shortTitle,
                           @RequestParam(value = "content") String content,
                           @RequestParam(value = "mainPhoto") MultipartFile mainPhoto,
                           @RequestParam(value = "tags") String[] tags) {
        Article article = articleService.saveArticle(mainTitle, shortTitle,
                accountService.getAccountByLogin(principal.getName()), content, tags);
        String newFileName = imageService
                .saveImage(mainPhoto,
                        "Minsk24Server\\src\\main\\resources\\static\\img\\articles",
                        Integer.toString(article.getId()));
    }
}