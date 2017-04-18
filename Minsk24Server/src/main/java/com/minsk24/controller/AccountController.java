package com.minsk24.controller;

import com.minsk24.bean.Account;
import com.minsk24.bean.Role;
import com.minsk24.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Account> getUsers() {
        return accountService.getAccounts();
    }

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    @ResponseBody
    public Account getCurrentUser(Principal principal) {
        return accountService.getAccountByLogin(principal.getName());
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public Account getUser(@RequestParam(value = "login") String username) {
        return accountService.getAccountByLogin(username);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password are invalid.");
        if (logout != null)
            model.addAttribute("logout", "You were successfully logout");
        return "login/loginPage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        return "registration/registrationPage";
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

    @RequestMapping(value = "/addAuthorPermissions", method = RequestMethod.POST)
    @ResponseBody
    public void addAuthorPermissions(@RequestBody String login) {
        Account account = accountService.getAccountByLogin(login);
        account.setRole(Role.AUTHOR);
        accountService.update(account);
    }

    @RequestMapping(value = "/removeAuthorPermissions", method = RequestMethod.POST)
    @ResponseBody
    public void removeAuthorPermissions(@RequestBody String login) {
        Account account = accountService.getAccountByLogin(login);
        account.setRole(Role.GUEST);
        accountService.update(account);
    }

    @RequestMapping(value = "/removeUser", method = RequestMethod.POST)
    public ResponseEntity removeUser(Principal principal, @RequestBody String login) {
        if (!principal.getName().equals(login)) {
            Account account = accountService.getAccountByLogin(login);
            if (account != null) accountService.delete(account);
            return ResponseEntity.ok().body(null);
        }
        else return ResponseEntity.unprocessableEntity().body(null);
    }
    public void autologin(String username, String password, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
