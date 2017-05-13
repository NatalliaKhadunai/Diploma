package com.minsk24.controller;

import com.minsk24.bean.*;
import com.minsk24.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BeforeEventRateService beforeEventRateService;
    @Autowired
    private AfterEventRateService afterEventRateService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Event> getEvents() {
        return eventService.getEvents();
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Event getEvent(@PathVariable Integer id) {
        return eventService.getEventById(id);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String addEvent(@RequestParam(required = false) Integer id,
                           @RequestParam(value = "title") String title,
                           @RequestParam(value = "location") String location,
                           @RequestParam(value = "description") String description,
                           @RequestParam(value = "mainPhoto") MultipartFile mainPhoto) {
        Event event = null;
        if (id != null) event = eventService.save(id, title, location, description);
        else event = eventService.save(title, location, description);
        String newFilename = imageService.saveImage(mainPhoto,
                "Minsk24Server\\src\\main\\resources\\static\\res\\img\\event",
                Integer.toString(event.getId()));
        return "redirect:/home";
    }

    @RequestMapping(value = "/events/{id}/comments", method = RequestMethod.POST)
    @ResponseBody
    public Event addComment(Principal principal, @PathVariable Integer id,
                                    @RequestBody Comment comment) {
        Event event = eventService.getEventById(id);
        Account account = accountService.getAccountByLogin(principal.getName());
        comment.setPublisher(account);
        comment.setPublishDate(new Timestamp(System.currentTimeMillis()));
        event.addComment(comment);
        return eventService.save(event);
    }

    @RequestMapping(value = "/events/{id}/beforeEventRate", method = RequestMethod.POST)
    @ResponseBody
    public void addBeforeEventChoice(Principal principal, @PathVariable Integer id,
                                     @RequestBody String eventUserChoice) {
        Event event = eventService.getEventById(id);
        Account account = accountService.getAccountByLogin(principal.getName());
        BeforeEventRate beforeEventRate = new BeforeEventRate();
        beforeEventRate.setEvent(event);
        beforeEventRate.setUser(account);
        beforeEventRate.setEventUserChoice(EventUserChoice.getFromValue(eventUserChoice));
        beforeEventRateService.save(beforeEventRate);
    }

    @RequestMapping(value = "/events/{id}/afterEventRate", method = RequestMethod.POST)
    @ResponseBody
    public void addAfterEventChoice(Principal principal, @PathVariable Integer id,
                                     @RequestBody Integer rate) {
        Event event = eventService.getEventById(id);
        Account account = accountService.getAccountByLogin(principal.getName());
        AfterEventRate afterEventRate = new AfterEventRate();
        afterEventRate.setUser(account);
        afterEventRate.setEvent(event);
        afterEventRate.setRate(rate);
        afterEventRateService.save(afterEventRate);
    }

    @RequestMapping(value = "/users/{login}/beforeEventRates", method = RequestMethod.GET)
    @ResponseBody
    public List<BeforeEventRate> getBeforeEventRatesByAccount(@PathVariable String login) {
        Account account = accountService.getAccountByLogin(login);
        return beforeEventRateService.getBeforeEventRatesByAccount(account);
    }

    @RequestMapping(value = "/users/{login}/afterEventRates", method = RequestMethod.GET)
    @ResponseBody
    public List<AfterEventRate> getAfterEventRatesByAccount(@PathVariable String login) {
        Account account = accountService.getAccountByLogin(login);
        return afterEventRateService.getAfterEventRatesByAccount(account);
    }
}
