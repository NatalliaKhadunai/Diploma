package com.minsk24.repository;

import com.minsk24.bean.Account;
import com.minsk24.bean.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByLogin(String login);
}
