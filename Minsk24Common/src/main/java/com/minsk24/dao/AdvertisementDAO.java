package com.minsk24.dao;

import com.minsk24.bean.Advertisement;

import java.util.List;

public interface AdvertisementDAO {
    Advertisement save(Advertisement advertisement);
    List<Advertisement> getAdvertisements();
}
