package com.minsk24.repository;

import com.minsk24.bean.Account;
import com.minsk24.bean.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findByPublisher(Account account);
}
