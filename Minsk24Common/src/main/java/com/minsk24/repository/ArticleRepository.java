package com.minsk24.repository;

import com.minsk24.bean.Account;
import com.minsk24.bean.Article;
import com.minsk24.bean.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {
    List<Article> findByTags(Tag tag, Pageable pageable);
    List<Article> findByAuthor(Account account, Pageable pageable);
    List<Article> findByAuthorAndTags(Account author, Tag tag, Pageable pageable);
    Integer countByAuthor(Account account);
    Integer countByTags(Tag tag);
    Integer countByAuthorAndTags(Account author, Tag tag);
    @Query(value = "SELECT * FROM ARTICLE WHERE ART_ID IN " +
            "(SELECT ARTICLE_ID FROM article_tag WHERE TAG_ID IN :tagIds " +
            "GROUP BY ARTICLE_ID ORDER BY COUNT(TAG_ID) DESC)", nativeQuery = true)
    List<Article> findByInterestingTags(@Param(value = "tagIds") List<Integer> tagIds);
}
