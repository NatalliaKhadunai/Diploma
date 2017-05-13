package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.BeforeEventRate;
import com.minsk24.repository.BeforeEventRateRepository;
import com.minsk24.service.BeforeEventRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeforeEventRateServiceImpl implements BeforeEventRateService {
    @Autowired
    private BeforeEventRateRepository beforeEventRateRepository;

    @Override
    public BeforeEventRate save(BeforeEventRate beforeEventRate) {
        return beforeEventRateRepository.save(beforeEventRate);
    }

    @Override
    public List<BeforeEventRate> getBeforeEventRatesByAccount(Account account) {
        return beforeEventRateRepository.findByUser(account);
    }
}
