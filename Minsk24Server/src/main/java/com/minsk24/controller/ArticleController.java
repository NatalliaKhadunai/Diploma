package com.minsk24.controller;

import com.minsk24.model.Article;
import com.minsk24.repository.ArticleRepository;
import com.minsk24.repository.UserRepository;
import com.minsk24.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.HashSet;

@RestController
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping (value = "/articles", method = RequestMethod.GET)
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @RequestMapping (value = "/addArticle", method = RequestMethod.POST)
    public Article addArticle(Principal principal,
                              @RequestParam(value = "id") String id,
                           @RequestParam(value = "mainTitle") String mainTitle,
                           @RequestParam(value = "shortTitle") String shortTitle,
                           @RequestParam(value = "content") String content,
                           @RequestParam(value = "mainPhoto") MultipartFile mainPhoto,
                           @RequestParam(value = "tags") String[] tags) {
        Article article = new Article();
        if (id != null) article.setId(id);
        article.setMainTitle(mainTitle);
        article.setShortTitle(shortTitle);
        article.setContent(content);
        article.setTags(new HashSet<>());
        for (String tagStr : tags) {
            article.getTags().add(tagStr);
        }
        article.setAuthor(userRepository.findByUsername(principal.getName()));
        article.setPublishDate(new Timestamp(System.currentTimeMillis()));
        article = articleRepository.save(article);
        String newFileName = imageService
                .saveImage(mainPhoto, "Minsk24Server\\src\\main\\resources\\static\\img\\articles", article.getId());
        article.setMainPhoto(newFileName);
        articleRepository.save(article);
        return article;
    }
}
