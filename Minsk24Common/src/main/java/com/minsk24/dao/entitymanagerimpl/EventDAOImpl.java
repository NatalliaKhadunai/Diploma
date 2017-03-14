package com.minsk24.dao.entitymanagerimpl;

import com.minsk24.bean.Event;
import com.minsk24.dao.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
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
