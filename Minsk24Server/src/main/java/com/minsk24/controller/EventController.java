package com.minsk24.controller;

import com.minsk24.model.Event;
import com.minsk24.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController (value = "/events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public Iterable<Event> getEvents() {
        return eventRepository.findAll();
    }

    @RequestMapping (value = "/addEvent", method = RequestMethod.POST)
    public void addEvent(Event event) {
        eventRepository.save(event);
    }
}
