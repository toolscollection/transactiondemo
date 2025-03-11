package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransferDTO {
    @NotBlank(message = "转出账号不能为空")
    private String fromAccount;

    @NotBlank(message = "转入账号不能为空")
    private String toAccount;

    @NotNull(message = "转账金额不能为空")
    @DecimalMin(value = "0.01", message = "转账金额必须大于0")
    private BigDecimal amount;
}