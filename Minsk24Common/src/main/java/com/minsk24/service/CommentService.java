package com.minsk24.service;

import com.minsk24.bean.Account;
import com.minsk24.bean.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);
    List<Comment> getCommentsByAccount(Account account);
}
