package com.minsk24.controller;

import com.minsk24.bean.Account;
import com.minsk24.bean.Role;
import com.minsk24.exception.BadRequestException;
import com.minsk24.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/v3")
public class AdminController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<Account> getUsers() {
        return accountService.getAccounts();
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
    public Account getUser(@PathVariable(value = "login") String login) {
        return accountService.getAccountByLogin(login);
    }

    @RequestMapping(value = "/users/{id}/addAuthorPermissions", method = RequestMethod.POST)
    public void addAuthorPermissions(@PathVariable(value = "id") Integer id,
                                     Principal principal) {
        Account account = accountService.getAccountById(id);
        if (account.getLogin().equals(principal.getName())) throw new BadRequestException("You cannot manage your roles");
        if (!account.getRole().equals(Role.AUTHOR)) {
            account.setRole(Role.AUTHOR);
            accountService.update(account);
        }
        else throw new BadRequestException("User already has that role");
    }

    @RequestMapping(value = "/users/{id}/removeAuthorPermissions", method = RequestMethod.POST)
    public void removeAuthorPermissions(@PathVariable(value = "id") Integer id,
                                        Principal principal) {
        Account account = accountService.getAccountById(id);
        if (account.getLogin().equals(principal.getName())) throw new BadRequestException("You cannot manage your roles");
        if (account.getRole().equals(Role.AUTHOR)) {
            account.setRole(Role.GUEST);
            accountService.update(account);
        }
        else throw new BadRequestException("This user doesn't have author role");
    }

    @RequestMapping(value = "/users/{id}/addAdminPermissions", method = RequestMethod.POST)
    public void addAdminPermissions(@PathVariable(value = "id") Integer id,
                                    Principal principal) {
        Account account = accountService.getAccountById(id);
        if (account.getLogin().equals(principal.getName())) throw new BadRequestException("You cannot manage your roles");
        if (!account.getRole().equals(Role.ADMIN)) {
            account.setRole(Role.ADMIN);
            accountService.update(account);
        }
        else throw new BadRequestException("User already has that role");
    }

    @RequestMapping(value = "/users/{id}/removeAdminPermissions", method = RequestMethod.POST)
    public void removeAdminPermissions(@PathVariable(value = "id") Integer id,
                                       Principal principal) {
        Account account = accountService.getAccountById(id);
        if (account.getLogin().equals(principal.getName())) throw new BadRequestException("You cannot manage your roles");
        if (account.getRole().equals(Role.ADMIN)) {
            account.setRole(Role.GUEST);
            accountService.update(account);
        }
        else throw new BadRequestException("This user doesn't have author role");
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable(value = "id") Integer id,
                           Principal principal) {
        Account account = accountService.getAccountById(id);
        if (account.getLogin().equals(principal.getName())) throw new BadRequestException("You cannot manage your roles");
        accountService.delete(account);
    }
}
