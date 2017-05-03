package com.minsk24.service.impl;

import com.minsk24.bean.Event;
import com.minsk24.repository.EventRepository;
import com.minsk24.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventDAO;

    @Override
    public Event save(String title, String location, String description) {
        Event event = new Event();
        event.setTitle(title);
        event.setLocation(location);
        event.setDescription(description);
        return eventDAO.save(event);
    }

    @Override
    public Event save(Integer id, String title, String location, String description) {
        Event event = new Event();
        event.setId(id);
        event.setTitle(title);
        event.setLocation(location);
        event.setDescription(description);
        return eventDAO.save(event);
    }

    @Override
    public Iterable<Event> getEvents() {
        return eventDAO.findAll();
    }

    @Override
    public Event getEventById(Integer id) {
        return eventDAO.findOne(id);
    }
}
