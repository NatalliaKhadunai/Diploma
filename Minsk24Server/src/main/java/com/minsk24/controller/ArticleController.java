package com.minsk24.controller;

import com.minsk24.bean.*;
import com.minsk24.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private AccountService accountService;

    private Integer ARTICLE_PER_PAGE = 10;

    @RequestMapping (value = "/articles", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Article> getArticles() {
        return articleService.getArticles();
    }

    @RequestMapping (value = "/articles/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Article getArticle(@PathVariable Integer id) {
        return articleService.getArticle(id);
    }

    @RequestMapping (value = "/articles", method = RequestMethod.POST)
    public String addArticle(Principal principal,
                             @RequestParam(required = false) Integer id,
                             @RequestParam(value = "title") String mainTitle,
                           @RequestParam(value = "shortDescription") String shortTitle,
                           @RequestParam(value = "content") String content,
                           @RequestParam(value = "mainPhoto") MultipartFile mainPhoto,
                           @RequestParam(value = "tags") String[] tags) {
        Article article = null;
        if (id != null) article = articleService.saveArticle(id, mainTitle, shortTitle,
                accountService.getAccountByLogin(principal.getName()), content, tags);
        else article = articleService.saveArticle(mainTitle, shortTitle,
                accountService.getAccountByLogin(principal.getName()), content, tags);
        imageService.saveImage(mainPhoto,
                        "Minsk24Server\\src\\main\\resources\\static\\res\\img\\article",
                        Integer.toString(article.getId()));
        return "redirect:/home";
    }
}