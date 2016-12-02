package com.minsk24.controller;

import com.minsk24.model.Advertisement;
import com.minsk24.model.User;
import com.minsk24.repository.AdvertisementRepository;
import com.minsk24.repository.UserRepository;
import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController (value = "/advertisements")
public class AdvertisementController {
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public Iterable<Advertisement> getAdvertisements() {
        return advertisementRepository.findAll();
    }

    @RequestMapping (value = "/addAdvertisement", method = RequestMethod.POST)
    public void addAdvertisement(Principal principal, @RequestBody Advertisement advertisement) {
        User user = userRepository.findByUsername(principal.getNickname());
        advertisement.setHolderId(user.getId());
        advertisementRepository.save(advertisement);
    }
}
