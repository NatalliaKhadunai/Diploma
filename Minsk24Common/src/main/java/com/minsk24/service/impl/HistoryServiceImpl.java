package com.minsk24.service.impl;

import com.minsk24.bean.History;
import com.minsk24.repository.HistoryRepository;
import com.minsk24.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public History addHistoryArticle(History history) {
        return historyRepository.save(history);
    }
}
