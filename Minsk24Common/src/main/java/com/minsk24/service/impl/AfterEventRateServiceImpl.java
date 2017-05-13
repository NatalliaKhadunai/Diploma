package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.AfterEventRate;
import com.minsk24.repository.AfterEventRateRepository;
import com.minsk24.service.AfterEventRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AfterEventRateServiceImpl implements AfterEventRateService {
    @Autowired
    private AfterEventRateRepository afterEventRateRepository;

    @Override
    public AfterEventRate save(AfterEventRate afterEventRate) {
        return afterEventRateRepository.save(afterEventRate);
    }

    @Override
    public List<AfterEventRate> getAfterEventRatesByAccount(Account account) {
        return afterEventRateRepository.findByUser(account);
    }
}
