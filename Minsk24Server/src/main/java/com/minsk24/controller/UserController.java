package com.minsk24.controller;

import com.minsk24.model.User;
import com.minsk24.repository.UserRepository;
import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController (value = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping (value = "/login", method = RequestMethod.POST)
    public void login(Principal principal) {

    }
}
