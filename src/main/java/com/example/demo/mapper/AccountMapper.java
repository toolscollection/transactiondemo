package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    //
    default int deductBalance(String accountNo, BigDecimal amount) {
        LambdaUpdateWrapper<Account> accountLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        accountLambdaUpdateWrapper.setSql("balance = balance - " + amount);
        accountLambdaUpdateWrapper.eq(Account::getAccountNo, accountNo);
        accountLambdaUpdateWrapper.ge(Account::getBalance, amount);
        return update(null, accountLambdaUpdateWrapper);
    }

    default int addBalance(String accountNo, BigDecimal amount) {
        LambdaUpdateWrapper<Account> accountLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        accountLambdaUpdateWrapper.setSql("balance = balance + " + amount);
        accountLambdaUpdateWrapper.eq(Account::getAccountNo, accountNo);
        return update(null, accountLambdaUpdateWrapper);
    }


}
