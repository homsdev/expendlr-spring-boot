package com.homs4j.expendlr.app.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class Account {
    private String accountId;
    private String alias;
    private BigDecimal balance;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private User user;
}
