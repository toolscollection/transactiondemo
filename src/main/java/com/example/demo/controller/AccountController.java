package com.example.demo.controller;

import com.example.demo.Account;
import com.example.demo.dto.Result;
import com.example.demo.dto.TransferDTO;
import com.example.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/transfer")
    public Result transfer(@Valid @RequestBody TransferDTO transferDTO) {
        try {
            String fromAccount = transferDTO.getFromAccount();
            String toAccount = transferDTO.getToAccount();
            BigDecimal amount = transferDTO.getAmount();

            accountService.transfer(fromAccount, toAccount, amount);
            return Result.success();
        } catch (Exception e) {
            log.error("转账失败", e);
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/{accountNo}")
    public Result getAccount(@PathVariable String accountNo) {
        Account account = null;//accountService.getAccountByNo(accountNo);
        return Result.success(account);
    }
}
