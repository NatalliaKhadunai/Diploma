package com.minsk24.service;

import com.minsk24.model.Article;

/**
 * Created by khadunai on 12/1/2016.
 */
public interface ArticleService {
    Iterable<Article> getArticles();
}
