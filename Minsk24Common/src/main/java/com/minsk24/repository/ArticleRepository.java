package com.minsk24.repository;

import com.minsk24.bean.Account;
import com.minsk24.bean.Article;
import com.minsk24.bean.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {
    List<Article> findByTags(Tag tag);
    List<Article> findByAuthor(Account account, Pageable pageable);
    Integer countByAuthor(Account account);
}
