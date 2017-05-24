package com.minsk24.service;

import com.minsk24.bean.Event;

import java.sql.Timestamp;
import java.util.List;

public interface EventService {
    Event save(String title, String location, Timestamp time, String description);
    Event save(Integer id, String title, String location, Timestamp time, String description);
    Event save(Event event);
    Iterable<Event> getEvents(Integer pageNum);
    Event getEventById(Integer id);
    Integer getNumberOfEvents();
    List<Event> getEventByTime(Timestamp timestamp, Integer pageNum);
    List<Event> getEventByLocation(String location, Integer pageNum);
    Integer getNumberOfEventsByTime(Timestamp timestamp);
    Integer getNumberOfEventsByLocation(String location);
}
