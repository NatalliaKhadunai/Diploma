package com.minsk24.repository;

import com.minsk24.bean.Account;
import com.minsk24.bean.AfterEventRate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AfterEventRateRepository
        extends CrudRepository<AfterEventRate, Integer> {
    List<AfterEventRate> findByUser(Account account);
}
