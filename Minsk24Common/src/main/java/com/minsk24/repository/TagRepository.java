package com.minsk24.repository;

import com.minsk24.bean.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Integer> {
    Tag findByName(String name);
    @Query(value = "SELECT TAG_ID FROM article_tag GROUP BY TAG_ID ORDER BY (COUNT(TAG_ID)) DESC",
    nativeQuery = true)
    List<Integer> findPopularTags();
}
