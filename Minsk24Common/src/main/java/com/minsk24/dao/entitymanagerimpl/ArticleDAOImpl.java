package com.minsk24.dao.entitymanagerimpl;

import com.minsk24.bean.Article;
import com.minsk24.dao.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
    @Autowired
    private EntityManager em;
    private String GET_ARTICLES = "from Article";

    @Override
    public Article saveArticle(Article article) {
        em.persist(article);
        return article;
    }

    @Override
    public List<Article> getArticles() {
        List<Article> articles = em.createQuery(GET_ARTICLES).getResultList();
        return articles;
    }
}
