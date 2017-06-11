package com.minsk24.service;

import com.minsk24.bean.History;

import java.util.List;

public interface HistoryService {
    History addHistoryArticle(History history);
    List<Integer> getHistoryIds();
    History getHistoryById(Integer id);
    List<History> getAllHistory();
}
