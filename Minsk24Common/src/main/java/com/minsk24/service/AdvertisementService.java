package com.minsk24.service;

import com.minsk24.bean.Account;
import com.minsk24.bean.Advertisement;
import com.minsk24.dto.AdvertisementMinDTO;

import java.sql.Date;
import java.util.List;

public interface AdvertisementService {
    Advertisement save(String title, String content, Account publisher, Date expirationDate);
    Iterable<AdvertisementMinDTO> getAdvertisements();
}
