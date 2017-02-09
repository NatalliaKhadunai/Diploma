package com.minsk24.controller;

import com.minsk24.bean.Advertisement;
import com.minsk24.service.AccountService;
import com.minsk24.service.AdvertisementService;
import com.minsk24.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Date;

@RestController
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ImageService imageService;

    @RequestMapping (value = "/advertisements", method = RequestMethod.GET)
    public Iterable<Advertisement> getAdvertisements() {
        return advertisementService.getAdvertisements();
    }

    @RequestMapping (value = "/addAdvertisement", method = RequestMethod.POST)
    public void addAdvertisement(Principal principal,
                                 @RequestParam(value = "title") String title,
                                 @RequestParam(value = "description") String description,
                                 @RequestParam(value = "expirationDate") Date expirationDate,
                                 @RequestParam(value = "mainPhoto") MultipartFile mainPhoto) {
        Advertisement advertisement = advertisementService.save(title, description, accountService.getAccountByLogin(principal.getName()), expirationDate);
        String newFilename = imageService.saveImage(mainPhoto,
                "Minsk24Server\\src\\main\\resources\\static\\img\\advertisements",
                Integer.toString(advertisement.getId()));
        }
}
