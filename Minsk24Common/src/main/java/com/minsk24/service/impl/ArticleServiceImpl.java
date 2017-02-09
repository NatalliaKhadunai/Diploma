package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.Article;
import com.minsk24.bean.Tag;
import com.minsk24.dao.ArticleDAO;
import com.minsk24.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Article saveArticle(String mainTitle, String shortTitle, Account author, String content, String[] tags) {
            Article article = new Article();
            article.setMainTitle(mainTitle);
            article.setShortTitle(shortTitle);
            article.addAuthor(author);
            article.setContent(content);
            article.setTags(new HashSet<Tag>());
            for (String tagStr : tags) {
                Tag tag = new Tag();
                tag.setName(tagStr);
                article.getTags().add(tag);
            }
            //TODO: get first and last names of logged author
            //article.addAuthor(accountService.getAccountByLogin(principal.getName()));
            article.setPublishDate(new Timestamp(System.currentTimeMillis()));
            return articleDAO.saveArticle(article);
    }

    @Override
    public List<Article> getArticles() {
        return articleDAO.getArticles();
    }
}
