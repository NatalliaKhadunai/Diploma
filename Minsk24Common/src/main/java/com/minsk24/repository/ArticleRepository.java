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
    @Query(value = "SELECT ART.* FROM ARTICLE ART JOIN " +
            "(SELECT ARTICLE_ID, COUNT(TAG_ID) AS NUM_OF_TAGS FROM article_tag " +
            "WHERE TAG_ID IN :tagIds GROUP BY ARTICLE_ID) TMP ON ART.ART_ID=TMP.ARTICLE_ID " +
            "ORDER BY ART.PUBLISH_DATE DESC, TMP.NUM_OF_TAGS DESC LIMIT :startIndex, :endIndex", nativeQuery = true)
    List<Article> findByInterestingTags(@Param(value = "tagIds") List<Integer> tagIds,
                                        @Param(value = "startIndex") Integer startIndex,
                                        @Param(value = "endIndex") Integer endIndex);
    @Query(value = "SELECT COUNT(*) FROM ARTICLE ART JOIN " +
            "  (SELECT ARTICLE_ID, COUNT(TAG_ID) AS NUM_OF_TAGS FROM article_tag " +
            "  WHERE TAG_ID IN :tagIds GROUP BY ARTICLE_ID) TMP ON ART.ART_ID=TMP.ARTICLE_ID " +
            "ORDER BY ART.PUBLISH_DATE DESC, TMP.NUM_OF_TAGS DESC", nativeQuery = true)
    Integer countByInterestingTags(@Param(value = "tagIds") List<Integer> tagIds);
}
