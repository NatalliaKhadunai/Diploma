package com.minsk24.dao.entitymanagerimpl;

import com.minsk24.bean.Account;
import com.minsk24.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {
    @Autowired
    private EntityManager em;
    private String GET_ACCOUNT_BY_LOGIN_HQL = "from Account where login=:login";
    private String GET_ACCOUNTS = "from Account";

    @Override
    public Account save(Account account) {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        return account;
    }

    @Override
    public void update(Account account) {
        em.merge(account);
    }

    @Override
    public void delete(Account account) {
        em.remove(account);
    }

    @Override
    public Account getAccountByLogin(String login) {
        Query query = em.createQuery(GET_ACCOUNT_BY_LOGIN_HQL);
        query.setParameter("login", login);
        Account account = null;
        try {
            account = (Account) query.getSingleResult();
        } catch (NoResultException e) {

        }
        return account;
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = em.createQuery(GET_ACCOUNTS).getResultList();
        return accounts;
    }
}
