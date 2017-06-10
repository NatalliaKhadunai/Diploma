package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.Advertisement;
import com.minsk24.repository.AdvertisementRepository;
import com.minsk24.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementRepository advertisementDAO;
    private int PAGE_SIZE = 3;

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
    public Advertisement save(Integer id, String title, String description, Account publisher, Date expirationDate) {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(id);
        advertisement.setTitle(title);
        advertisement.setDescription(description);
        advertisement.setPlacementDate(new Date(System.currentTimeMillis()));
        advertisement.setExpirationDate(expirationDate);
        advertisement.setHolder(publisher);
        return advertisementDAO.save(advertisement);
    }

    @Override
    public Advertisement save(Advertisement advertisement) {
        return advertisementDAO.save(advertisement);
    }

    @Override
    public Iterable<Advertisement> getAdvertisements(Integer pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "placementDate"));
        return advertisementDAO.findAll(pageRequest).getContent();
    }

    @Override
    public Advertisement getAdvertisementById(Integer id) {
        return advertisementDAO.findOne(id);
    }

    @Override
    public Integer getNumberOfAdvertisements() {
        return (int)Math.ceil((double)advertisementDAO.count() / PAGE_SIZE);
    }

    @Override
    public List<Advertisement> getAdvertisementsByHolder(Account account, Integer pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "placementDate"));
        return advertisementDAO.findByHolder(account, pageRequest);
    }

    @Override
    public Integer getNumberOfAdvertisementsOfHolder(Account account) {
        return (int)Math.ceil((double)advertisementDAO.countByHolder(account) / PAGE_SIZE);
    }

    @Override
    public List<Advertisement> getExpiringAdvertisements(Integer pageNum) {
        Date date = new Date(System.currentTimeMillis());
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.ASC, "expirationDate"));
        return advertisementDAO.findByExpirationDateGreaterThan(date, pageRequest);
    }

    @Override
    public Integer getNumberOfExpiringAdvertisements() {
        return (int)Math.ceil((double)advertisementDAO
                        .countByExpirationDateGreaterThan(new Date(System.currentTimeMillis()))
                        / PAGE_SIZE);
    }

    @Override
    public List<Advertisement> searchByKeyword(String keyword, Integer pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "expirationDate"));
        return advertisementDAO.findByTitleIgnoreCaseContaining(keyword, pageRequest);
    }

    @Override
    public Integer getNumberOfAdvertisementsSearchByKeyword(String keyword) {
        return (int)Math.ceil((double)advertisementDAO.countByTitleIgnoreCaseContaining(keyword) / PAGE_SIZE);
    }

    @Override
    public void removeAdvertisement(Advertisement advertisement) {
        advertisementDAO.delete(advertisement);
    }
}
