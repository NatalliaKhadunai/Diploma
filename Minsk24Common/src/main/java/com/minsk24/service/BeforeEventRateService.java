package com.minsk24.service;

import com.minsk24.bean.Account;
import com.minsk24.bean.BeforeEventRate;

import java.util.List;

public interface BeforeEventRateService {
    BeforeEventRate save(BeforeEventRate beforeEventRate);
    List<BeforeEventRate> getBeforeEventRatesByAccount(Account account);
}
