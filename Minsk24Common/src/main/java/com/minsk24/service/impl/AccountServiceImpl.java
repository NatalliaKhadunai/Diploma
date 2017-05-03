package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.Role;
import com.minsk24.repository.AccountRepository;
import com.minsk24.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountDAO;

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
        accountDAO.save(account);
    }

    @Override
    public void delete(Account account) {
        accountDAO.delete(account);
    }

    @Override
    public Account getAccountById(Integer id) {
        return accountDAO.findOne(id);
    }

    @Override
    public Account getAccountByLogin(String login) {
        return accountDAO.findByLogin(login);
    }

    @Override
    public Iterable<Account> getAccounts() {
        return accountDAO.findAll();
    }
}
