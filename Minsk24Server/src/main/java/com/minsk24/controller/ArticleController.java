package com.minsk24.controller;

import com.minsk24.model.Article;
import com.minsk24.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping (value = "/articles", method = RequestMethod.GET)
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @RequestMapping (value = "/addArticle", method = RequestMethod.POST)
    public void addArticle(@RequestBody Article article) {
        article.setPublishDate(new Timestamp(System.currentTimeMillis()));
        articleRepository.save(article);
    }
}
