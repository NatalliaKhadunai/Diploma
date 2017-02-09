package com.minsk24.service;

import com.minsk24.bean.Event;

import java.util.List;

public interface EventService {
    Event save(String title, String location, String description);
    List<Event> getEvents();
}