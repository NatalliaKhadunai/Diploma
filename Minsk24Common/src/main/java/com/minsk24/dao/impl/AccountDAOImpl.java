package com.minsk24.dao.impl;

import com.minsk24.bean.Account;
import com.minsk24.dao.AccountDAO;
import com.minsk24.util.SessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDAOImpl implements AccountDAO {
    private String GET_ACCOUNT_BY_LOGIN_HQL = "from Account where login=:login";
    private String GET_ACCOUNTS = "from Account";

    @Override
    public Account save(Account account) {
        Session session = SessionFactoryUtil.getSession();
        session.save(account);
        session.close();
        return account;
    }

    @Override
    public void update(Account account) {
        Session session = SessionFactoryUtil.getSession();
        session.update(account);
        session.close();
    }

    @Override
    public void delete(Account account) {
        Session session = SessionFactoryUtil.getSession();
        session.delete(account);
        session.close();
    }

    @Override
    public Account getAccountByLogin(String login) {
        Session session = SessionFactoryUtil.getSession();
        Query query = session.createQuery(GET_ACCOUNT_BY_LOGIN_HQL);
        query.setParameter("login", login);
        Account account = (Account) query.uniqueResult();
        session.close();
        return account;
    }

    @Override
    public List<Account> getAccounts() {
        Session session = SessionFactoryUtil.getSession();
        List<Account> accounts = session.createQuery(GET_ACCOUNTS).list();
        session.close();
        return accounts;
    }
}