package com.minsk24.controller;

import com.minsk24.bean.Event;
import com.minsk24.service.EventService;
import com.minsk24.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private ImageService imageService;

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
}
