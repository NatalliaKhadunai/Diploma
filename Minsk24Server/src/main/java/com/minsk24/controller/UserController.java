package com.minsk24.controller;

import com.minsk24.model.Role;
import com.minsk24.model.User;
import com.minsk24.repository.UserRepository;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    @ResponseBody
    public User getCurrentUser(Principal principal) {
        return userRepository.findByUsername(principal.getName());
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@RequestParam(value = "username") String username) {
        return userRepository.findByUsername(username);
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        return "login/loginPage";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        return "registration/registrationPage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("username") String username,
                               @ModelAttribute("password") String password,
                               @ModelAttribute("passwordConfirm") String passwordConfirm) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.VISITOR);
        userRepository.save(user);
        autologin(user.getUsername(), user.getPassword());

        return "redirect:/home";
    }

    @RequestMapping(value = "/addAuthorPermissions", method = RequestMethod.POST)
    @ResponseBody
    public void addAuthorPermissions(@RequestBody String userId) {
        User user = userRepository.findOne(userId);
        user.setRole(Role.AUTHOR);
        userRepository.save(user);
    }

    @RequestMapping(value = "/removeAuthorPermissions", method = RequestMethod.POST)
    @ResponseBody
    public void removeAuthorPermissions(@RequestBody String userId) {
        User user = userRepository.findOne(userId);
        user.setRole(Role.VISITOR);
        userRepository.save(user);
    }

    @RequestMapping(value = "/removeUser", method = RequestMethod.POST)
    public ResponseEntity removeUser(Principal principal, @RequestBody String username) {
        if (!principal.getName().equals(username)) {
            userRepository.delete(username);
            return ResponseEntity.ok().body(null);
        }
        else return ResponseEntity.unprocessableEntity().body(null);
    }
    public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}
