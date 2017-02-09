package com.minsk24.dao.impl;

import com.minsk24.bean.Article;
import com.minsk24.dao.ArticleDAO;
import com.minsk24.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDAOImpl implements ArticleDAO {
    private String GET_ARTICLES = "from Article";
    @Override
    public Article saveArticle(Article article) {
        Session session = SessionFactoryUtil.getSession();
        session.save(article);
        session.close();
        return article;
    }

    @Override
    public List<Article> getArticles() {
        Session session = SessionFactoryUtil.getSession();
        List<Article> articles = session.createQuery(GET_ARTICLES).list();
        session.close();
        return articles;
    }
}