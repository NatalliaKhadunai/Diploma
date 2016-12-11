package com.minsk24.controller;

import com.minsk24.model.Advertisement;
import com.minsk24.model.User;
import com.minsk24.repository.AdvertisementRepository;
import com.minsk24.repository.UserRepository;
import com.minsk24.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Date;

@RestController
public class AdvertisementController {
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageService imageService;

    @RequestMapping (value = "/advertisements", method = RequestMethod.GET)
    public Iterable<Advertisement> getAdvertisements() {
        return advertisementRepository.findAll();
    }

    @RequestMapping (value = "/addAdvertisement", method = RequestMethod.POST)
    public void addAdvertisement(Principal principal,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "content") String content,
                                 @RequestParam(value = "placementDate") Date placementDate,
                                 @RequestParam(value = "expirationDate") Date expirationDate,
                                 @RequestParam(value = "mainPhoto") MultipartFile mainPhoto) {
        Advertisement advertisement = new Advertisement();
        advertisement.setName(name);
        advertisement.setContent(content);
        advertisement.setPlacementDate(placementDate);
        advertisement.setExpirationDate(expirationDate);
        advertisement.setHolder(userRepository.findByUsername(principal.getName()));
        advertisement = advertisementRepository.save(advertisement);
        String newFilename = imageService.saveImage(mainPhoto,
                "Minsk24Server\\src\\main\\resources\\static\\img\\advertisements", advertisement.getId());
        advertisement.setMainPhoto(newFilename);
        advertisementRepository.save(advertisement);
    }
}
