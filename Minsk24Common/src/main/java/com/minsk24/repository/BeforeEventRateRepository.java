package com.minsk24.repository;

import com.minsk24.bean.Account;
import com.minsk24.bean.BeforeEventRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface BeforeEventRateRepository
        extends CrudRepository<BeforeEventRate, Integer> {
    @Query("SELECT BER FROM BeforeEventRate BER WHERE event.time>=:today and user=:user")
    List<BeforeEventRate> findByUser(@Param(value = "today") Date today,
                                     @Param(value = "user") Account account);
}
