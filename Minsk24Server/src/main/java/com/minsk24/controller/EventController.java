package com.minsk24.controller;

import com.minsk24.bean.Event;
import com.minsk24.service.EventService;
import com.minsk24.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private ImageService imageService;

    @RequestMapping (value = "/events", method = RequestMethod.GET)
    public Iterable<Event> getEvents() {
        return eventService.getEvents();
    }

    @RequestMapping (value = "/addEvent", method = RequestMethod.POST)
    public void addEvent(@RequestParam(value = "title") String title,
                         @RequestParam(value = "location") String location,
                         @RequestParam(value = "description") String description,
                         @RequestParam(value = "mainPhoto") MultipartFile mainPhoto) {
        Event event = eventService.save(title, location, description);
        String newFilename = imageService.saveImage(mainPhoto,
                "Minsk24Server\\src\\main\\resources\\static\\img\\events",
                Integer.toString(event.getId()));
    }
}
