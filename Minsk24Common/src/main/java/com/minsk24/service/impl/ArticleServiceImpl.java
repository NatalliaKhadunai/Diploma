package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.Article;
import com.minsk24.bean.Tag;
import com.minsk24.repository.ArticleRepository;
import com.minsk24.repository.TagRepository;
import com.minsk24.service.AccountService;
import com.minsk24.service.ArticleService;
import com.minsk24.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleRepository articleDAO;
    @Autowired
    private TagService tagService;
    private int PAGE_SIZE = 3;

    @Override
    public Article saveArticle(String mainTitle, String shortTitle, Account author, String content, Integer[] tags) {
            Article article = new Article();
            article.setTitle(mainTitle);
            article.setShortDescription(shortTitle);
            article.setAuthor(author);
            article.setContent(content);
            article.setTags(new HashSet<Tag>());
            for (Integer tagId : tags) {
                article.getTags().add(tagService.getTagById(tagId));
            }
            article.setPublishDate(new Timestamp(System.currentTimeMillis()));
            return articleDAO.save(article);
    }

    @Override
    public Article saveArticle(Integer id, String mainTitle, String shortTitle, Account author, String content, Integer[] tags) {
        Article article = new Article();
        article.setId(id);
        article.setTitle(mainTitle);
        article.setShortDescription(shortTitle);
        article.setAuthor(author);
        article.setContent(content);
        article.setTags(new HashSet<Tag>());
        for (Integer tagId : tags) {
            article.getTags().add(tagService.getTagById(tagId));
        }
        article.setPublishDate(new Timestamp(System.currentTimeMillis()));
        return articleDAO.save(article);
    }

    @Override
    public Article saveArticle(Article article) {
        return articleDAO.save(article);
    }

    @Override
    public List<Article> getArticles(Integer pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "publishDate"));
        return articleDAO.findAll(pageRequest).getContent();
    }

    @Override
    public Article getArticle(Integer id) {
        return articleDAO.findOne(id);
    }

    @Override
    public List<Article> getArticlesByTag(Tag tag, Integer pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "publishDate"));
        return articleDAO.findByTags(tag, pageRequest);
    }

    @Override
    public List<Article> getArticlesByAuthor(Account author, Integer pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "publishDate"));
        return articleDAO.findByAuthor(author, pageRequest);
    }

    @Override
    public Integer getNumberOfArticles() {
        return (int)Math.ceil((double)articleDAO.count() / PAGE_SIZE);
    }

    @Override
    public Integer getNumberOfArticlesOfAuthor(Account author) {
        return (int)Math.ceil((double)articleDAO.countByAuthor(author) / PAGE_SIZE);
    }

    @Override
    public Integer getNumberOfArticlesByTag(Tag tag) {
        return (int)Math.ceil((double)articleDAO.countByTags(tag) / PAGE_SIZE);
    }
    
    @Override 
    public List<Article> getArticlesByAuthorAndTag(Account author, Tag tag, Integer pageNum) {
    	PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "publishDate"));
    	return articleDAO.findByAuthorAndTags(author, tag, pageRequest);
    }

    @Override
    public Integer getNumberOfArticlesByAuthorAndTag(Account author, Tag tag) {
        return (int)Math.ceil((double)articleDAO.countByAuthorAndTags(author, tag) / PAGE_SIZE);
    }

    @Override
    public List<Article> getArticlesByInterestingTags(List<Tag> tags, Integer pageNum) {
        List<Integer> tagIds = tags.stream().map(n -> n.getId()).collect(Collectors.toList());
        int startIndex = (pageNum - 1) * PAGE_SIZE;
        int endIndex = pageNum * PAGE_SIZE;
        return articleDAO.findByInterestingTags(tagIds, startIndex, endIndex);
    }

    @Override
    public Integer getNumberOfArticlesByInterestingTags(List<Tag> tags) {
        List<Integer> tagIds = tags.stream().map(n -> n.getId()).collect(Collectors.toList());
        return (int)Math.ceil((double)articleDAO.countByInterestingTags(tagIds) / PAGE_SIZE);
    }
}
