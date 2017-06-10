package com.minsk24.service;

import com.minsk24.bean.Account;
import com.minsk24.bean.Advertisement;

import java.sql.Date;
import java.util.List;

public interface AdvertisementService {
    Advertisement save(String title, String content, Account publisher, Date expirationDate);
    Advertisement save(Integer id, String title, String content, Account publisher, Date expirationDate);
    Advertisement save(Advertisement advertisement);
    Iterable<Advertisement> getAdvertisements(Integer pageNum);
    Advertisement getAdvertisementById(Integer id);
    Integer getNumberOfAdvertisements();
    List<Advertisement> getAdvertisementsByHolder(Account account, Integer pageNum);
    Integer getNumberOfAdvertisementsOfHolder(Account account);
    List<Advertisement> getExpiringAdvertisements(Integer pageNum);
    Integer getNumberOfExpiringAdvertisements();
    List<Advertisement> searchByKeyword(String keyword, Integer pageNum);
    Integer getNumberOfAdvertisementsSearchByKeyword(String keyword);
    void removeAdvertisement(Advertisement advertisement);
}
