package com.minsk24.service.impl;

import com.minsk24.bean.History;
import com.minsk24.repository.HistoryRepository;
import com.minsk24.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public History addHistoryArticle(History history) {
        return historyRepository.save(history);
    }

    @Override
    public List<Integer> getHistoryIds() {
        return historyRepository.getIds();
    }

    @Override
    public History getHistoryById(Integer id) {
        return historyRepository.findOne(id);
    }

    @Override
    public List<History> getAllHistory() {
        return historyRepository.findAllByOrderByEndYearAsc();
    }
}
