package com.minsk24.service;

import com.minsk24.bean.Account;
import com.minsk24.bean.Role;

import java.util.List;

public interface AccountService {
    Account save(String login, String password, Role role);
    void update(Account account);
    void delete(Account account);
    Account getAccountByLogin(String login);
    List<Account> getAccounts();
}
