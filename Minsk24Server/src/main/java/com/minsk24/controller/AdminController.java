package com.minsk24.controller;

import com.minsk24.bean.Account;
import com.minsk24.bean.Role;
import com.minsk24.exception.BadRequestException;
import com.minsk24.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<Account> getUsers() {
        return accountService.getAccounts();
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
    public Account getUser(@PathVariable String login) {
        return accountService.getAccountByLogin(login);
    }

    @RequestMapping(value = "/users/{login}/addAuthorPermissions", method = RequestMethod.POST)
    public void addAuthorPermissions(@PathVariable String login, Principal principal) {
        if (login.equals(principal.getName())) throw new BadRequestException("You cannot manage your roles");
        Account account = accountService.getAccountByLogin(login);
        if (!account.getRole().equals(Role.AUTHOR)) {
            account.setRole(Role.AUTHOR);
            accountService.update(account);
        }
        else throw new BadRequestException("User already has that role");
    }

    @RequestMapping(value = "/users/{login}/removeAuthorPermissions", method = RequestMethod.POST)
    public void removeAuthorPermissions(@PathVariable String login, Principal principal) {
        if (login.equals(principal.getName())) throw new BadRequestException("You cannot manage your roles");
        Account account = accountService.getAccountByLogin(login);
        if (account.getRole().equals(Role.AUTHOR)) {
            account.setRole(Role.GUEST);
            accountService.update(account);
        }
        else throw new BadRequestException("This user doesn't have author role");
    }

    @RequestMapping(value = "/users/{login}/addAdminPermissions", method = RequestMethod.POST)
    public void addAdminPermissions(@PathVariable String login, Principal principal) {
        if (login.equals(principal.getName())) throw new BadRequestException("You cannot manage your roles");
        Account account = accountService.getAccountByLogin(login);
        if (!account.getRole().equals(Role.ADMIN)) {
            account.setRole(Role.ADMIN);
            accountService.update(account);
        }
        else throw new BadRequestException("User already has that role");
    }

    @RequestMapping(value = "/users/{login}/removeAdminPermissions", method = RequestMethod.POST)
    public void removeAdminPermissions(@PathVariable String login, Principal principal) {
        if (login.equals(principal.getName())) throw new BadRequestException("You cannot manage your roles");
        Account account = accountService.getAccountByLogin(login);
        if (account.getRole().equals(Role.ADMIN)) {
            account.setRole(Role.GUEST);
            accountService.update(account);
        }
        else throw new BadRequestException("This user doesn't have author role");
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable String login, Principal principal) {
        if (login.equals(principal.getName())) throw new BadRequestException("You cannot manage your roles");
        Account account = accountService.getAccountByLogin(login);
        accountService.delete(account);
    }
}
