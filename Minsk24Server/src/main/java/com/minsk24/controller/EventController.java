package com.minsk24.controller;

import com.minsk24.bean.*;
import com.minsk24.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
    public Iterable<Event> getEvents(@RequestParam Integer page,
                                     @RequestParam(required = false) Long time,
                                     @RequestParam(required = false) String location,
                                     @RequestParam(required = false)
                                                 String sort,
                                     @RequestParam(required = false) Boolean past) {
        Timestamp timestamp = null;
        if (time != null) timestamp = new Timestamp(time);
        if (sort != null && sort.equals("rating")) {
            if (time != null)
                return eventService.getTopRatedUpcomingEventsByTime(page, timestamp);
            else if (location != null)
                return eventService.getTopRatedUpcomingEventsByLocation(page, location);
            else return eventService.getTopRatedUpcomingEvents(page);
        }
        else {
            if (time != null)
                return eventService.getEventByTime(timestamp, page);
            else if (location != null) return eventService.getEventByLocation(location, page);
            else return eventService.getEvents(page);
        }
    }

    @RequestMapping(value = "/events/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer getNumberOfEvents(@RequestParam(required = false) Long time,
                                     @RequestParam(required = false) String location) {
        if (time != null) {
            Timestamp timestamp = new Timestamp(time);
            return eventService.getNumberOfEventsByTime(timestamp);
        }
        else if (location != null) return eventService.getNumberOfEventsByLocation(location);
        else return eventService.getNumberOfEvents();
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
                           @RequestParam(value = "time") String timeStr,
                           @RequestParam(value = "description") String description,
                           @RequestParam(value = "mainPhoto") MultipartFile mainPhoto) {
        Event event = null;
        if (StringUtils.countOccurrencesOf(timeStr, ":") == 1) timeStr += ":00";
        Timestamp time = Timestamp.valueOf(timeStr.replace("T"," "));
        if (id != null) event = eventService.save(id, title, location, time, description);
        else event = eventService.save(title, location, time, description);
        if (mainPhoto != null && !mainPhoto.getOriginalFilename().isEmpty())
        imageService.saveImage(mainPhoto,
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
    public void addBeforeEventChoice(@PathVariable Integer id,
                                     @RequestBody String eventUserChoice,
                                     Principal principal) {
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
    public void addAfterEventChoice(@PathVariable Integer id,
                                     @RequestBody Integer rate,
                                    Principal principal) {
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
