package com.minsk24.dao;

import com.minsk24.bean.Article;

import java.util.List;

public interface ArticleDAO {
    Article saveArticle(Article article);
    List<Article> getArticles();
}
