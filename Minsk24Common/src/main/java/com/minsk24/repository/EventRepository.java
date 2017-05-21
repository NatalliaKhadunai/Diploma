package com.minsk24.repository;

import com.minsk24.bean.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {
    List<Event> findByTimeBetween(Date start, Date end, Pageable pageable);
    List<Event> findByLocationIgnoreCaseContaining(String location, Pageable pageable);
    Integer countByTimeBetween(Date start, Date end);
    Integer countByLocationIgnoreCaseContaining(String location);
}
