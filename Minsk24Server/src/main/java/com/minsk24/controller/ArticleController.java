package com.minsk24.controller;

import com.minsk24.bean.*;
import com.minsk24.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;

    private Integer ARTICLE_PER_PAGE = 10;

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Article> getArticles(@RequestParam Integer pageNum) {
        return articleService.getArticles(pageNum);
    }

    @RequestMapping(value = "/articles/interesting", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Article> getInterestingArticles(@RequestParam Integer pageNum,
                                         Principal principal) {
        Account account = accountService.getAccountByLogin(principal.getName());
        return articleService.getArticlesByInterestingTags(account.getInterestingTags());
    }

    @RequestMapping(value = "/articles/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer getNumberOfArticles() {
        return articleService.getNumberOfArticles();
    }

    @RequestMapping(value = "/articles/author/{login}/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer getNumberOfArticlesOfAuthor(@PathVariable String login) {
        Account account = accountService.getAccountByLogin(login);
        return articleService.getNumberOfArticlesOfAuthor(account);
    }

    @RequestMapping(value = "/articles/tags/{tagName}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Article> getArticlesByTag(@PathVariable String tagName,
                                              @RequestParam Integer pageNum) {
        Tag tag = tagService.getTagByName(tagName);
        return articleService.getArticlesByTag(tag, pageNum);
    }

    @RequestMapping(value = "/articles/tags/{tagName}/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer getArticlesByTag(@PathVariable String tagName) {
        Tag tag = tagService.getTagByName(tagName);
        return articleService.getNumberOfArticlesByTag(tag);
    }

    @RequestMapping(value = "/articles/authors/{login}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Article> getArticlesByAuthor(@PathVariable String login,
                                                 @RequestParam Integer pageNum) {
        Account author = accountService.getAccountByLogin(login);
        return articleService.getArticlesByAuthor(author, pageNum);
    }
    
    @RequestMapping(value = "/articles/authors/{login}/tags/{tagName}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Article> getArticlesByAuthorAndTag(@PathVariable String login,
                                                       @PathVariable String tagName,
                                                 @RequestParam Integer pageNum) {
        Account author = accountService.getAccountByLogin(login);
        Tag tag = tagService.getTagByName(tagName);
        return articleService.getArticlesByAuthorAndTag(author, tag, pageNum);
    }

    @RequestMapping(value = "/articles/authors/{login}/tags/{tagName}/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer getNumberOfArticlesByAuthorAndTag(@PathVariable String login, @PathVariable String tagName) {
        Account author = accountService.getAccountByLogin(login);
        Tag tag = tagService.getTagByName(tagName);
        return articleService.getNumberOfArticlesByAuthorAndTag(author, tag);
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Article getArticle(@PathVariable Integer id) {
        return articleService.getArticle(id);
    }

    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    public String addArticle(Principal principal,
                             @RequestParam(required = false) Integer id,
                             @RequestParam(value = "title") String mainTitle,
                           @RequestParam(value = "shortDescription") String shortTitle,
                           @RequestParam(value = "content") String content,
                           @RequestParam(value = "mainPhoto") MultipartFile mainPhoto,
                           @RequestParam(value = "tags") Integer[] tags) {
        Article article = null;
        if (id != null) article = articleService.saveArticle(id, mainTitle, shortTitle,
                accountService.getAccountByLogin(principal.getName()), content, tags);
        else article = articleService.saveArticle(mainTitle, shortTitle,
                accountService.getAccountByLogin(principal.getName()), content, tags);
        imageService.saveImage(mainPhoto,
                        "Minsk24Server\\src\\main\\resources\\static\\res\\img\\article",
                        Integer.toString(article.getId()));
        return "redirect:/home";
    }

    @RequestMapping(value = "/articles/{id}/comments", method = RequestMethod.POST)
    @ResponseBody
    public Article addComment(Principal principal, @PathVariable Integer id,
                              @RequestBody Comment comment) {
        Article article = articleService.getArticle(id);
        Account account = accountService.getAccountByLogin(principal.getName());
        comment.setPublisher(account);
        comment.setPublishDate(new Timestamp(System.currentTimeMillis()));
        article.addComment(comment);
        return articleService.saveArticle(article);
    }
}