package com.minsk24.service;

import com.minsk24.bean.Account;
import com.minsk24.bean.Advertisement;

import java.sql.Date;

public interface AdvertisementService {
    Advertisement save(String title, String content, Account publisher, Date expirationDate);
    Advertisement save(Integer id, String title, String content, Account publisher, Date expirationDate);
    Advertisement save(Advertisement advertisement);
    Iterable<Advertisement> getAdvertisements();
    Advertisement getAdvertisementById(Integer id);
}
