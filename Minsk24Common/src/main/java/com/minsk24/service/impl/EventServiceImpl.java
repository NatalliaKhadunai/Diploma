package com.minsk24.service.impl;

import com.minsk24.bean.BeforeEventRate;
import com.minsk24.bean.Event;
import com.minsk24.bean.EventUserChoice;
import com.minsk24.repository.EventRepository;
import com.minsk24.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

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
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "time"));
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
    public List<Event> getEventByTime(Timestamp timestamp, Integer pageNum) {
        Date today = getTodayDate(timestamp);
        Date tomorrow = getTomorrowDate(timestamp);
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "time"));
        return eventDAO.findByTimeBetween(today, tomorrow, pageRequest);
    }

    @Override
    public List<Event> getEventByLocation(String location, Integer pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "time"));
        return eventDAO.findByLocationIgnoreCaseContaining(location, pageRequest);
    }

    @Override
    public Integer getNumberOfEventsByTime(Timestamp timestamp) {
        Date today = getTodayDate(timestamp);
        Date tomorrow = getTomorrowDate(timestamp);
        return (int)Math.ceil((double)eventDAO.countByTimeBetween(today, tomorrow) / PAGE_SIZE);
    }

    @Override
    public Integer getNumberOfEventsByLocation(String location) {
        return (int)Math.ceil((double)eventDAO.countByLocationIgnoreCaseContaining(location) / PAGE_SIZE);
    }

    @Override
    public List<Event> getTopRatedUpcomingEvents(Integer pageNum) {
        int startIndex = (pageNum - 1) * PAGE_SIZE;
        int endIndex = pageNum * PAGE_SIZE;
        return eventDAO.findTopRatedUpcomingEvents(startIndex, endIndex);
    }

    @Override
    public List<Event> getTopRatedUpcomingEventsByTime(Integer pageNum, Timestamp timestamp) {
        Date today = getTodayDate(timestamp);
        Date tomorrow = getTomorrowDate(timestamp);
        int startIndex = (pageNum - 1) * PAGE_SIZE;
        int endIndex = pageNum * PAGE_SIZE;
        return eventDAO.findTopRatedUpcomingEventsByTime(today.toString(), tomorrow.toString(),
                startIndex, endIndex);
    }

    @Override
    public List<Event> getTopRatedUpcomingEventsByLocation(Integer pageNum, String location) {
        int startIndex = (pageNum - 1) * PAGE_SIZE;
        int endIndex = pageNum * PAGE_SIZE;
        return eventDAO.findTopRatedUpcomingEventsByLocation("%" + location + "%",
                startIndex, endIndex);
    }

    @Override
    public Integer getNumberOfTopRatedUpcomingEvents() {
        return (int)Math.ceil((double)eventDAO.countTopRatedUpcomingEvents() / PAGE_SIZE);
    }

    @Override
    public Integer getNumberOfTopRatedUpcomingEventsByTime(Timestamp timestamp) {
        Date today = getTodayDate(timestamp);
        Date tomorrow = getTomorrowDate(timestamp);
        return (int)Math.ceil((double)eventDAO
                .countTopRatedUpcomingEventsByTime(today.toString(), tomorrow.toString()) / PAGE_SIZE);
    }

    @Override
    public Integer getNumberOfTopRatedUpcomingEventsByLocation(String location) {
        return (int)Math.ceil((double)eventDAO
                .countTopRatedUpcomingEventsByLocation(location) / PAGE_SIZE);
    }

    @Override
    public List<Event> searchByKeyword(String keyword, Integer pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "time"));
        return eventDAO.findByTitleIgnoreCaseContaining(keyword, pageRequest);
    }

    @Override
    public Integer getNumberOfEventsSearchByKeyword(String keyword) {
        return (int)Math.ceil((double)eventDAO.countByTitleIgnoreCaseContaining(keyword) / PAGE_SIZE);
    }

    @Override
    public List<Event> getTopRatedPastEvents(Integer pageNum) {
        int startIndex = (pageNum - 1) * PAGE_SIZE;
        int endIndex = pageNum * PAGE_SIZE;
        return eventDAO.findTopRatedPastEvents(startIndex, endIndex);
    }

    @Override
    public List<Event> getTopRatedPastEventsByTime(Timestamp timestamp, Integer pageNum) {
        int startIndex = (pageNum - 1) * PAGE_SIZE;
        int endIndex = pageNum * PAGE_SIZE;
        Date today = getTodayDate(timestamp);
        Date tomorrow = getTomorrowDate(timestamp);
        return eventDAO.findTopRatedPastEventsByTime(today.toString(),
                tomorrow.toString(), startIndex, endIndex);
    }

    @Override
    public List<Event> getTopRatedPastEventsByLocation(String location, Integer pageNum) {
        int startIndex = (pageNum - 1) * PAGE_SIZE;
        int endIndex = pageNum * PAGE_SIZE;
        return eventDAO.findTopRatedPastEventsByLocation(location, startIndex, endIndex);
    }

    @Override
    public Integer getNumberOfTopRatedPastEvents() {
        return (int)Math.ceil((double)eventDAO.countTopRatedPastEvents() / PAGE_SIZE);
    }

    @Override
    public Integer getNumberOfTopRatedPastEventsByTime(Timestamp timestamp) {
        Date today = getTodayDate(timestamp);
        Date tomorrow = getTomorrowDate(timestamp);
        return (int)Math.ceil((double)eventDAO
                .countTopRatedPastEventsByTime(today.toString(), tomorrow.toString()) / PAGE_SIZE);
    }

    @Override
    public Integer getNumberOfTopRatedPastEventsByLocation(String location) {
        return (int)Math.ceil((double)eventDAO
                .countTopRatedPastEventsByLocation(location) / PAGE_SIZE);
    }

    @Override
    public void removeEvent(Event event) {
        eventDAO.delete(event);
    }

    private Date getTodayDate(Timestamp timestamp) {
        Date specTime = new Date(timestamp.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(specTime);
        int hourOfDate = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.add(Calendar.HOUR_OF_DAY, -hourOfDate);
        Date today = new Date(calendar.getTimeInMillis());
        return today;
    }

    private Date getTomorrowDate(Timestamp timestamp) {
        Date specTime = new Date(timestamp.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(specTime);
        int hourOfDate = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.add(Calendar.HOUR_OF_DAY, 24-hourOfDate);
        Date tomorrow = new Date(calendar.getTimeInMillis());
        return tomorrow;
    }
}
