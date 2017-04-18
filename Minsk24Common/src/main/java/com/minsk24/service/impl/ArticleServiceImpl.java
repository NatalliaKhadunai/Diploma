package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.Article;
import com.minsk24.bean.Tag;
import com.minsk24.dto.ArticleMinDTO;
import com.minsk24.repository.ArticleRepository;
import com.minsk24.repository.TagRepository;
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
            //TODO: get first and last names of logged author
            //article.addAuthor(accountService.getAccountByLogin(principal.getName()));
            article.setPublishDate(new Timestamp(System.currentTimeMillis()));
            return articleDAO.save(article);
    }

    @Override
    public List<ArticleMinDTO> getArticles() {
        Iterable<Article> articles = articleDAO.findAll();
        List<ArticleMinDTO> articleMinDTOS = new ArrayList<>();
        for (Article article : articles) {
            ArticleMinDTO articleMinDTO = new ArticleMinDTO();
            articleMinDTO.setId(article.getId());
            articleMinDTO.setTitle(article.getTitle());
            articleMinDTO.setShortDescription(article.getShortDescription());
            articleMinDTO.setPublishDate(article.getPublishDate());
            for (Tag tag : article.getTags()) articleMinDTO.addTag(tag.getName());
            articleMinDTOS.add(articleMinDTO);
        }
        return articleMinDTOS;
    }

    @Override
    public Article getArticle(Integer id) {
        return articleDAO.findOne(id);
    }
}
