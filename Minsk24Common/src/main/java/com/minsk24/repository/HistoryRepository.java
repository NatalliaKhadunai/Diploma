package com.minsk24.repository;

import com.minsk24.bean.History;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<History, Integer> {
    @Query(value = "SELECT id FROM History ORDER BY END_YEAR ASC")
    List<Integer> getIds();
    List<History> findAllByOrderByEndYearAsc();
}
