package com.minsk24.service.impl;

import com.minsk24.bean.Event;
import com.minsk24.repository.EventRepository;
import com.minsk24.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventDAO;
    private int PAGE_SIZE = 3;

    @Override
    public Event save(String title, String location, Timestamp time, String description) {
        Event event = new Event();
        event.setTitle(title);
        event.setLocation(location);
        event.setTime(time);
        event.setDescription(description);
        return eventDAO.save(event);
    }

    @Override
    public Event save(Integer id, String title, String location, Timestamp time, String description) {
        Event event = new Event();
        event.setId(id);
        event.setTitle(title);
        event.setLocation(location);
        event.setTime(time);
        event.setDescription(description);
        return eventDAO.save(event);
    }

    @Override
    public Event save(Event event) {
        return eventDAO.save(event);
    }

    @Override
    public Iterable<Event> getEvents(Integer pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE);
        return eventDAO.findAll(pageRequest).getContent();
    }

    @Override
    public Event getEventById(Integer id) {
        return eventDAO.findOne(id);
    }

    @Override
    public Integer getNumberOfEvents() {
        return (int)Math.ceil((double)eventDAO.count() / PAGE_SIZE);
    }

    @Override
    public List<Event> getEventByDate(Timestamp timestamp, Integer pageNum) {
        Date today = new Date(timestamp.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = new Date(calendar.getTimeInMillis());
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE);
        return eventDAO.findByTimeBetween(today, tomorrow, pageRequest);
    }

    @Override
    public List<Event> getEventByLocation(String location, Integer pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE);
        return eventDAO.findByLocationIgnoreCaseContaining(location, pageRequest);
    }

    @Override
    public Integer getNumberOfEventsByTime(Timestamp timestamp) {
        Date today = new Date(timestamp.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = new Date(calendar.getTimeInMillis());
        return (int)Math.ceil((double)eventDAO.countByTimeBetween(today, tomorrow) / PAGE_SIZE);
    }

    @Override
    public Integer getNumberOfEventsByLocation(String location) {
        return (int)Math.ceil((double)eventDAO.countByLocationIgnoreCaseContaining(location) / PAGE_SIZE);
    }
}
