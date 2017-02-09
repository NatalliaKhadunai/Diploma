package com.minsk24.dao;

import com.minsk24.bean.Event;

import java.util.List;

public interface EventDAO {
    Event save(Event event);
    List<Event> getEvents();
}
