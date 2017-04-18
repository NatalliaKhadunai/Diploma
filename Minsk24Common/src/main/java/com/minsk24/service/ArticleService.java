package com.minsk24.service;

import com.minsk24.bean.Account;
import com.minsk24.bean.Article;
import com.minsk24.dto.ArticleMinDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    Article saveArticle(String mainTitle, String shortTitle, Account author, String content, String[] tags);
    List<ArticleMinDTO> getArticles();
    Article getArticle(Integer id);
}
