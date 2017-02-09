package com.minsk24.dao.impl;

import com.minsk24.bean.Event;
import com.minsk24.dao.EventDAO;
import com.minsk24.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventDAOImpl implements EventDAO {
    private String GET_EVENTS = "from Event";

    @Override
    public Event save(Event event) {
        Session session = SessionFactoryUtil.getSession();
        session.save(event);
        session.close();
        return event;
    }

    @Override
    public List<Event> getEvents() {
        Session session = SessionFactoryUtil.getSession();
        List<Event> events = session.createQuery(GET_EVENTS).list();
        session.close();
        return events;
    }
}
