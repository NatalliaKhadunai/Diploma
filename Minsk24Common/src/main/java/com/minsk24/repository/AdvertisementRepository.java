package com.minsk24.repository;

import com.minsk24.bean.Account;
import com.minsk24.bean.Advertisement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdvertisementRepository extends PagingAndSortingRepository<Advertisement, Integer> {
    List<Advertisement> findByHolder(Account account, Pageable pageable);
    Integer countByHolder(Account account);
}
