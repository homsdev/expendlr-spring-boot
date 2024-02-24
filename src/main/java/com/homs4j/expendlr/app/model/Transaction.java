package com.homs4j.expendlr.app.model;

import com.homs4j.expendlr.app.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class Transaction {
    private String transactionId;
    private String title;
    private TransactionType type;
    private Timestamp date;
    private BigDecimal amount;
    private Account account;
    private Category category;
}
