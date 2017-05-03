package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.Article;
import com.minsk24.bean.Tag;
import com.minsk24.repository.ArticleRepository;
import com.minsk24.repository.TagRepository;
import com.minsk24.service.AccountService;
import com.minsk24.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleRepository articleDAO;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Article saveArticle(String mainTitle, String shortTitle, Account author, String content, String[] tags) {
            Article article = new Article();
            article.setTitle(mainTitle);
            article.setShortDescription(shortTitle);
            article.addAuthor(author);
            article.setContent(content);
            article.setTags(new HashSet<Tag>());
            for (String tagStr : tags) {
                article.getTags().add(tagRepository.findByName(tagStr));
            }
            article.setPublishDate(new Timestamp(System.currentTimeMillis()));
            return articleDAO.save(article);
    }

    @Override
    public Article saveArticle(Integer id, String mainTitle, String shortTitle, Account author, String content, String[] tags) {
        Article article = new Article();
        article.setId(id);
        article.setTitle(mainTitle);
        article.setShortDescription(shortTitle);
        article.addAuthor(author);
        article.setContent(content);
        article.setTags(new HashSet<Tag>());
        for (String tagStr : tags) {
            article.getTags().add(tagRepository.findByName(tagStr));
        }
        article.setPublishDate(new Timestamp(System.currentTimeMillis()));
        return articleDAO.save(article);
    }

    @Override
    public Iterable<Article> getArticles() {
        return articleDAO.findAll();
    }

    @Override
    public Article getArticle(Integer id) {
        return articleDAO.findOne(id);
    }
}
