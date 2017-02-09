package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.Role;
import com.minsk24.dao.AccountDAO;
import com.minsk24.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Account save(String login, String password, Role role) {
        Account account = new Account();
        account.setLogin(login);
        account.setPassword(password);
        account.setRole(role);
        return accountDAO.save(account);
    }

    @Override
    public void update(Account account) {
        accountDAO.update(account);
    }

    @Override
    public void delete(Account account) {
        accountDAO.delete(account);
    }

    @Override
    public Account getAccountByLogin(String login) {
        return accountDAO.getAccountByLogin(login);
    }

    @Override
    public List<Account> getAccounts() {
        return accountDAO.getAccounts();
    }
}
