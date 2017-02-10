package com.minsk24.dao.entitymanagerimpl;

import com.minsk24.bean.Event;
import com.minsk24.dao.EventDAO;
import com.minsk24.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Component
public class EventDAOImpl implements EventDAO {
    @Autowired
    private EntityManager em;
    private String GET_EVENTS = "from Event";

    @Override
    public Event save(Event event) {
        em.persist(event);
        return event;
    }

    @Override
    public List<Event> getEvents() {
        List<Event> events = em.createQuery(GET_EVENTS).getResultList();
        return events;
    }
}
