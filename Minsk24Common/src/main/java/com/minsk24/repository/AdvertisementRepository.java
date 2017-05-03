package com.minsk24.repository;

import com.minsk24.bean.Account;
import com.minsk24.bean.Advertisement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface AdvertisementRepository extends CrudRepository<Advertisement, Integer> {
    @Query("SELECT A.id, A.title, A.holder, A.placementDate, A.expirationDate FROM Advertisement A")
    List<Object[]> findAllMinified();
}
