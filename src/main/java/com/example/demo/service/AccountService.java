package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.Account;

import java.math.BigDecimal;

public interface AccountService extends IService<Account> {
    void transfer(String fromAccount, String toAccount, BigDecimal amount);
}
