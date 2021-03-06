package com.minsk24.controller;

import com.google.common.collect.Lists;
import com.minsk24.bean.Account;
import com.minsk24.bean.Role;
import com.minsk24.bean.Tag;
import com.minsk24.exception.BadRequestException;
import com.minsk24.exception.UserNotFoundException;
import com.minsk24.service.AccountService;
import com.minsk24.service.ImageService;
import com.minsk24.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TagService tagService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    @ResponseBody
    public Account getCurrentUser(Principal principal) {
        if (principal != null) return accountService.getAccountByLogin(principal.getName());
        else return null;
    }

    @RequestMapping(value = "/users/{login}/exists", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkUserExists(@PathVariable String login) {
        Account account = accountService.getAccountByLogin(login);
        if (account == null) return false;
        else return true;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error) {
        if (error != null)
            model.addAttribute("error", "Your username and password are invalid.");
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
            throw new BadRequestException("Your username and password is invalid");
        }
        Account account = accountService.save(login, password, Role.GUEST);
        autologin(account.getLogin(), account.getPassword(), request);
        return "redirect:/home";
    }

    @RequestMapping(value = "/v1/account/tags/interesting", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getInterstingTags(Principal principal) {
        Account account = accountService.getAccountByLogin(principal.getName());
        return account.getInterestingTags();
    }

    @RequestMapping(value = "/v1/account/tags/{tagId}/add", method = RequestMethod.POST)
    @ResponseBody
    public Tag addInterestingTag(@PathVariable Integer tagId, Principal principal) {
        Account account = accountService.getAccountByLogin(principal.getName());
        Tag tag = tagService.getTagById(tagId);
        account.getInterestingTags().add(tag);
        accountService.update(account);
        return tag;
    }

    @RequestMapping(value = "/v1/account/tags/{tagId}/exclude", method = RequestMethod.POST)
    @ResponseBody
    public Tag excludeInterestingTag(@PathVariable Integer tagId, Principal principal) {
        Account account = accountService.getAccountByLogin(principal.getName());
        Tag tag = tagService.getTagById(tagId);
        account.getInterestingTags().remove(tag);
        accountService.update(account);
        return tag;
    }

    @RequestMapping(value = "/v1/account/photo", method = RequestMethod.POST)
    public String chanheAccountPhoto(@RequestParam MultipartFile file, Principal principal) {
        Account account = accountService.getAccountByLogin(principal.getName());
        imageService.saveImage(file,
                "/var/www/DiplomaImages/account",
                Integer.toString(account.getId()));
        if (!account.getPhoto().equals(account.getId().toString())) {
            account.setPhoto(account.getId().toString());
            accountService.update(account);
        }
        return "redirect:/home";
    }

    public void autologin(String username, String password, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
