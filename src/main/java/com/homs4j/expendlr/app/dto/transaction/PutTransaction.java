package com.homs4j.expendlr.app.dto.transaction;

import com.homs4j.expendlr.app.enums.TransactionType;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Value
@Builder
public class PutTransaction {
    String title;
    BigDecimal amount;
    Timestamp date;
    TransactionType type;
}
