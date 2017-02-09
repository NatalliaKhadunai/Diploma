package com.minsk24.dao;

import com.minsk24.bean.Account;

import java.util.List;

public interface AccountDAO {
    Account save(Account account);
    void update(Account account);
    void delete(Account account);
    Account getAccountByLogin(String login);
    List<Account> getAccounts();
}
