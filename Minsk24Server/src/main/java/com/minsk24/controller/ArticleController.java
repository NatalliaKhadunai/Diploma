package com.minsk24.controller;

import com.minsk24.bean.Account;
import com.minsk24.bean.Article;
import com.minsk24.bean.Comment;
import com.minsk24.bean.Tag;
import com.minsk24.exception.BadRequestException;
import com.minsk24.exception.NotFoundException;
import com.minsk24.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Timestamp;

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
    public Iterable<Article> getArticles(@RequestParam Integer page,
                                         @RequestParam(value = "tag", required = false)
                                                 String tagName,
                                         @RequestParam(value = "author", required = false)
                                         String authorLogin,
                                         @RequestParam(value = "interesting", required = false)
                                                 Boolean showOnlyInteresting,
                                         Principal principal) {
        if (showOnlyInteresting != null && showOnlyInteresting == true && principal != null) {
            Account account = accountService.getAccountByLogin(principal.getName());
            if (account.getInterestingTags().size() != 0)
                return articleService.getArticlesByInterestingTags(account.getInterestingTags(), page);
            else return articleService.getArticles(page);
        } else if (tagName != null) {
            Tag tag = tagService.getTagByName(tagName);
            return articleService.getArticlesByTag(tag, page);
        }
        else if (authorLogin != null) {
            Account author = accountService.getAccountByLogin(authorLogin);
            return articleService.getArticlesByAuthor(author, page);
        } else return articleService.getArticles(page);
    }

    @RequestMapping(value = "/articles/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer getNumberOfArticles(@RequestParam(value = "tag", required = false)
                                               String tagName,
                                       @RequestParam(value = "author", required = false)
                                               String authorLogin,
                                       @RequestParam(value = "interesting", required = false)
                                               Boolean showOnlyInteresting,
                                       Principal principal) {
        if (showOnlyInteresting != null && showOnlyInteresting == true) {
            Account account = accountService.getAccountByLogin(principal.getName());
            if (account.getInterestingTags().size() != 0)
                return articleService.getNumberOfArticlesByInterestingTags(account.getInterestingTags());
            else return articleService.getNumberOfArticles();
        } else if (tagName != null) {
            Tag tag = tagService.getTagByName(tagName);
            return articleService.getNumberOfArticlesByTag(tag);
        } else if (authorLogin != null) {
            Account author = accountService.getAccountByLogin(authorLogin);
            return articleService.getNumberOfArticlesOfAuthor(author);
        } else return articleService.getNumberOfArticles();
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Article getArticle(@PathVariable Integer id) {
        return articleService.getArticle(id);
    }

    @RequestMapping(value = "/v2/articles/{id}", method = RequestMethod.DELETE)
    public String removeArticle(@PathVariable Integer id) {
        Article article = articleService.getArticle(id);
        if (article != null) articleService.removeArticle(article);
        else throw new NotFoundException("No such article");
        return "redirect:/home";
    }

    @RequestMapping(value = "/v2/articles", method = RequestMethod.POST)
    public String addArticle(@RequestParam(required = false) Integer id,
                             @RequestParam(value = "title") String mainTitle,
                             @RequestParam(value = "shortDescription") String shortTitle,
                             @RequestParam(value = "content") String content,
                             @RequestParam(value = "mainPhoto") MultipartFile mainPhoto,
                             @RequestParam(value = "tags") Integer[] tags,
                             Principal principal) {
        Article article = null;
        if (id != null) article = articleService.saveArticle(id, mainTitle, shortTitle,
                accountService.getAccountByLogin(principal.getName()), content, tags);
        else article = articleService.saveArticle(mainTitle, shortTitle,
                accountService.getAccountByLogin(principal.getName()), content, tags);
        if (mainPhoto != null && !mainPhoto.getOriginalFilename().isEmpty())
            imageService.saveImage(mainPhoto,
                    "Minsk24Server\\src\\main\\resources\\static\\res\\img\\article",
                    Integer.toString(article.getId()));
        return "redirect:/home";
    }

    @RequestMapping(value = "/v1/articles/{id}/comments", method = RequestMethod.POST)
    @ResponseBody
    public Article addComment(@PathVariable Integer id,
                              @RequestBody Comment comment,
                              Principal principal) {
        Article article = articleService.getArticle(id);
        Account account = accountService.getAccountByLogin(principal.getName());
        comment.setPublisher(account);
        comment.setPublishDate(new Timestamp(System.currentTimeMillis()));
        article.addComment(comment);
        return articleService.saveArticle(article);
    }
}