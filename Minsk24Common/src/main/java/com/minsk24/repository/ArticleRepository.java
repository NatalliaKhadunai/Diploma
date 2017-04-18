package com.minsk24.repository;

import com.minsk24.bean.Account;
import com.minsk24.bean.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
}
