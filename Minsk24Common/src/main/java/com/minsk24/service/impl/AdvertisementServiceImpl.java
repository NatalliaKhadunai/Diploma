package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.Advertisement;
import com.minsk24.dto.AdvertisementMinDTO;
import com.minsk24.repository.AdvertisementRepository;
import com.minsk24.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementRepository advertisementDAO;

    @Override
    public Advertisement save(String title, String description, Account publisher, Date expirationDate) {
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(title);
        advertisement.setDescription(description);
        advertisement.setPlacementDate(new Date(System.currentTimeMillis()));
        advertisement.setExpirationDate(expirationDate);
        advertisement.setHolder(publisher);
        return advertisementDAO.save(advertisement);
    }

    @Override
    public Iterable<AdvertisementMinDTO> getAdvertisements() {
        Iterable<Advertisement> advertisements = advertisementDAO.findAll();
        List<AdvertisementMinDTO> advertisementMinDTOS = new ArrayList<>();
        for (Advertisement advertisement : advertisements) {
            AdvertisementMinDTO advertisementMinDTO = new AdvertisementMinDTO();
            advertisementMinDTO.setId(advertisement.getId());
            advertisementMinDTO.setTitle(advertisement.getTitle());
            advertisementMinDTO.setExpirationDate(advertisement.getExpirationDate());
            advertisementMinDTO.setDescription(advertisement.getTitle());
            advertisementMinDTOS.add(advertisementMinDTO);
        }
        return advertisementMinDTOS;
    }
}
