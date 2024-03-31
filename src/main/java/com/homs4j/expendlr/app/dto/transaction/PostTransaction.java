package com.homs4j.expendlr.app.dto.transaction;

import com.homs4j.expendlr.app.enums.TransactionStatus;
import com.homs4j.expendlr.app.enums.TransactionType;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Value
@Builder
public class PostTransaction {
    String title;
    TransactionType type;
    Timestamp date;
    BigDecimal amount;
    String accountId;
    String categoryId;
    TransactionStatus status;
}
