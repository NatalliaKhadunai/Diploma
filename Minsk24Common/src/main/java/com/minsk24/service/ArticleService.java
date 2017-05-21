package com.minsk24.service;

import com.minsk24.bean.Account;
import com.minsk24.bean.Article;
import com.minsk24.bean.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    Article saveArticle(String mainTitle, String shortTitle, Account author, String content, Integer[] tags);
    Article saveArticle(Integer id, String mainTitle, String shortTitle, Account author, String content, Integer[] tags);
    Article saveArticle(Article article);
    Iterable<Article> getArticles(Integer pageNum);
    Article getArticle(Integer id);
    Iterable<Article> getArticlesByTag(Tag tag, Integer pageNum);
    Iterable<Article> getArticlesByAuthor(Account author, Integer pageNum);
    Integer getNumberOfArticles();
    Integer getNumberOfArticlesOfAuthor(Account author);
    Integer getNumberOfArticlesByTag(Tag tag);
}
