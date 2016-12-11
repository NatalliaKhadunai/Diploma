package com.minsk24.controller;

import com.minsk24.model.Event;
import com.minsk24.repository.EventRepository;
import com.minsk24.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ImageService imageService;

    @RequestMapping (value = "/events", method = RequestMethod.GET)
    public Iterable<Event> getEvents() {
        return eventRepository.findAll();
    }

    @RequestMapping (value = "/addEvent", method = RequestMethod.POST)
    public void addEvent(@RequestParam(value = "name") String name,
                         @RequestParam(value = "location") String location,
                         @RequestParam(value = "description") String description,
                         @RequestParam(value = "mainPhoto") MultipartFile mainPhoto) {
        Event event = new Event();
        event.setName(name);
        event.setLocation(location);
        event.setDescription(description);
        event = eventRepository.save(event);
        String newFilename = imageService.saveImage(mainPhoto,
                "Minsk24Server\\src\\main\\resources\\static\\img\\events", event.getId());
        event.setMainPhoto(newFilename);
        eventRepository.save(event);
    }
}
