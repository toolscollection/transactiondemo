package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Account;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transfer(String fromAccount, String toAccount, BigDecimal amount) {

        //参数校验
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("转账金额必须大于0");
        }

        //检查账户是否存在
        Account fromAcc = lambdaQuery().eq(Account::getAccountNo, fromAccount).one();

        Account toAcc = lambdaQuery().eq(Account::getAccountNo, toAccount).one();
        if (fromAcc == null && toAcc == null) {
            throw new RuntimeException("账户不存在");
        }

        //扣款
        boolean deductResult = lambdaUpdate().setSql("balance = balance - " + amount)
                .eq(Account::getAccountNo, fromAcc.getAccountNo())
                .ge(Account::getBalance, amount)
                .update();

        if (!deductResult) {
            throw new RuntimeException("扣款失败，可能余额不足");
        }

        //模拟随机异常，用于测试事务回滚
        if (Math.random() < 0.3) {
            throw new RuntimeException("模拟转账故障");
        }

        //加款
        boolean addResult = lambdaUpdate().setSql("balance = balance + " + amount)
                .eq(Account::getAccountNo, toAcc.getAccountNo())
                .update();

        if (!addResult) {
            throw new RuntimeException("加款失败");
        }
        log.info("转账成功：{} -> {} 金额：{}", fromAccount, toAccount, amount);
    }
}



