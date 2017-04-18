package com.minsk24.repository;

import com.minsk24.bean.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {
}
