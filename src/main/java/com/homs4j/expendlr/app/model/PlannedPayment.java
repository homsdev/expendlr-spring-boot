package com.homs4j.expendlr.app.model;

import com.homs4j.expendlr.app.enums.TimeInterval;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class PlannedPayment {
    private String plannedPaymentId;
    private String title;
    private BigDecimal amount;
    private Timestamp start;
    private Timestamp end;
    private TimeInterval interval;
    private Category category;
    private Account account;
}
