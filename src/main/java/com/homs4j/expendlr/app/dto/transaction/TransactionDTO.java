package com.homs4j.expendlr.app.dto.transaction;

import com.homs4j.expendlr.app.dto.category.CategoryDTO;
import com.homs4j.expendlr.app.enums.TransactionStatus;
import com.homs4j.expendlr.app.enums.TransactionType;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Value
@Builder
public class TransactionDTO {
    String transactionId;
    String title;
    TransactionType type;
    Timestamp date;
    BigDecimal amount;
    String accountId;
    CategoryDTO categoryDTO;
    TransactionStatus status;
}
