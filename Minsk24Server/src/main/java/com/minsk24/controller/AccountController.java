package com.minsk24.controller;

import com.minsk24.bean.Account;
import com.minsk24.bean.Role;
import com.minsk24.exception.UserNotFoundException;
import com.minsk24.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    @ResponseBody
    public Account getCurrentUser(Principal principal) {
        return accountService.getAccountByLogin(principal.getName());
    }

    @RequestMapping(value = "/users/{login}/exists", method = RequestMethod.GET)
    @ResponseBody
    public void checkUserExists(@PathVariable String login) {
        Account account = accountService.getAccountByLogin(login);
        if (account == null) throw new UserNotFoundException("User doesn't exist") ;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password are invalid.");
        if (logout != null)
            model.addAttribute("logout", "You were successfully logout");
        return "res/login/loginPage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        return "res/registration/registrationPage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("login") String login,
                               @ModelAttribute("password") String password,
                               @ModelAttribute("passwordConfirm") String passwordConfirm,
                               HttpServletRequest request) {
        if (!password.equals(passwordConfirm)) {
            return "Your username and password is invalid.";
        }
        Account account = accountService.save(login, password, Role.GUEST);
        autologin(account.getLogin(), account.getPassword(), request);
        return "redirect:/home";
    }

    public void autologin(String username, String password, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
