package com.minsk24.repository;

import com.minsk24.model.Advertisement;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by khadunai on 12/2/2016.
 */
public interface AdvertisementRepository extends CrudRepository<Advertisement, String> {
}
