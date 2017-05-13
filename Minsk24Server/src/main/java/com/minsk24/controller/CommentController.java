package com.minsk24.controller;

import com.minsk24.bean.Account;
import com.minsk24.bean.Comment;
import com.minsk24.service.AccountService;
import com.minsk24.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/users/{login}/comments", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> getCommentsByAccount(@PathVariable String login) {
        Account account = accountService.getAccountByLogin(login);
        return commentService.getCommentsByAccount(account);
    }
}
