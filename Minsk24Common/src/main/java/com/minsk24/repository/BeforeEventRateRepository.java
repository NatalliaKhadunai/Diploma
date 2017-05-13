package com.minsk24.repository;

import com.minsk24.bean.Account;
import com.minsk24.bean.BeforeEventRate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BeforeEventRateRepository
        extends CrudRepository<BeforeEventRate, Integer> {
    List<BeforeEventRate> findByUser(Account account);
}
