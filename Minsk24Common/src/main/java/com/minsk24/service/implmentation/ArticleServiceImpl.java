package com.minsk24.service.implmentation;

import com.minsk24.model.Article;
import com.minsk24.repository.ArticleRepository;
import com.minsk24.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by khadunai on 12/1/2016.
 */
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }
}
