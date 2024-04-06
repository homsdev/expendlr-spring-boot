package com.homs4j.expendlr.app.dto.transaction;

import com.homs4j.expendlr.app.enums.TransactionStatus;
import com.homs4j.expendlr.app.enums.TransactionType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Value
@Builder
public class PostTransaction {
    @NotBlank
    String title;
    @NotBlank
    TransactionType type;
    @NotBlank
    Timestamp date;
    @NotBlank @Digits(integer = 10, fraction = 2)
    BigDecimal amount;
    @NotBlank
    String accountId;
    @NotBlank
    String categoryId;
    @NotBlank
    TransactionStatus status;
}
