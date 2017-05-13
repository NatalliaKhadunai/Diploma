package com.minsk24.repository;

import com.minsk24.bean.Account;
import com.minsk24.bean.Article;
import com.minsk24.bean.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    List<Article> findByTags(Tag tag);
    List<Article> findByAuthor(Account account);
}
