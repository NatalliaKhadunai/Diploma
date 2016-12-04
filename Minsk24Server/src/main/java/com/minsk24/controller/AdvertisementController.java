package com.minsk24.controller;

import com.minsk24.model.Advertisement;
import com.minsk24.model.User;
import com.minsk24.repository.AdvertisementRepository;
import com.minsk24.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AdvertisementController {
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping (value = "/advertisements", method = RequestMethod.GET)
    public Iterable<Advertisement> getAdvertisements() {
        return advertisementRepository.findAll();
    }

    @RequestMapping (value = "/addAdvertisement", method = RequestMethod.POST)
    public void addAdvertisement(Principal principal, @RequestBody Advertisement advertisement) {
        User user = userRepository.findByUsername(principal.getName());
        advertisement.setHolderId(user.getId());
        advertisementRepository.save(advertisement);
    }
}
