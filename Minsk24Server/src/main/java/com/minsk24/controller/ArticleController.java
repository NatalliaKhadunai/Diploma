package com.minsk24.controller;

import com.minsk24.model.Article;
import com.minsk24.model.Tag;
import com.minsk24.repository.ArticleRepository;
import com.minsk24.service.ArticleService;
import com.minsk24.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@RestController
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ImageService imageService;

    @RequestMapping (value = "/articles", method = RequestMethod.GET)
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @RequestMapping (value = "/addArticle", method = RequestMethod.POST)
    public void addArticle(@RequestParam(value = "mainTitle") String mainTitle,
                           @RequestParam(value = "shortTitle") String shortTitle,
                           @RequestParam(value = "content") String content,
                           @RequestParam(value = "mainPhoto") MultipartFile mainPhoto,
                           @RequestParam(value = "tags") String[] tags) {
        Article article = new Article();
        article.setMainTitle(mainTitle);
        article.setShortTitle(shortTitle);
        article.setContent(content);
        article.setTags(new HashSet<>());
        for (String tagStr : tags) {
            article.getTags().add(tagStr);
        }
        article.setAuthorsId(new HashSet<>());
        article.setPublishDate(new Timestamp(System.currentTimeMillis()));
        article = articleRepository.save(article);
        imageService.saveImage(mainPhoto, "articles", article.getId());
        article.setMainPhoto(article.getId());
        articleRepository.save(article);
    }
}
