package com.minsk24.service;

import com.minsk24.bean.Account;
import com.minsk24.bean.AfterEventRate;

import java.util.List;

public interface AfterEventRateService {
    AfterEventRate save(AfterEventRate afterEventRate);
    List<AfterEventRate> getAfterEventRatesByAccount(Account account);
}
