package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.Comment;
import com.minsk24.repository.CommentRepository;
import com.minsk24.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByAccount(Account account) {
        return commentRepository.findByPublisher(account);
    }
}
