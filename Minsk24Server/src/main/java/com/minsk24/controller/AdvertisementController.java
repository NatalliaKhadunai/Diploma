package com.minsk24.controller;

import com.minsk24.bean.Account;
import com.minsk24.bean.Advertisement;
import com.minsk24.bean.Comment;
import com.minsk24.service.AccountService;
import com.minsk24.service.AdvertisementService;
import com.minsk24.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;

@Controller
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/advertisements", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Advertisement> getAdvertisements() {
        return advertisementService.getAdvertisements();
    }

    @RequestMapping(value = "/advertisements/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Advertisement getAdvertisement(@PathVariable Integer id) {
        return advertisementService.getAdvertisementById(id);
    }


    @RequestMapping(value = "/advertisements", method = RequestMethod.POST)
    public String addAdvertisement(Principal principal,
                                   @RequestParam(required = false) Integer id,
                                   @RequestParam String title,
                                   @RequestParam String description,
                                   @RequestParam Date expirationDate,
                                   @RequestParam MultipartFile mainPhoto) {
        Advertisement advertisement = null;
        if (id != null)
            advertisement = advertisementService.save(id, title, description, accountService.getAccountByLogin(principal.getName()), expirationDate);
        else
            advertisement = advertisementService.save(title, description, accountService.getAccountByLogin(principal.getName()), expirationDate);
        imageService.saveImage(mainPhoto,
                "Minsk24Server\\src\\main\\resources\\static\\res\\img\\advertisement",
                Integer.toString(advertisement.getId()));
        return "redirect:/home";
    }

    @RequestMapping(value = "/advertisements/{id}/comments", method = RequestMethod.POST)
    @ResponseBody
    public Advertisement addComment(Principal principal, @PathVariable Integer id,
                              @RequestBody Comment comment) {
        Advertisement advertisement = advertisementService.getAdvertisementById(id);
        Account account = accountService.getAccountByLogin(principal.getName());
        comment.setPublisher(account);
        comment.setPublishDate(new Timestamp(System.currentTimeMillis()));
        advertisement.addComment(comment);
        return advertisementService.save(advertisement);
    }
}
