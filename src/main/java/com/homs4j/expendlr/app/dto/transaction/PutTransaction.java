package com.homs4j.expendlr.app.dto.transaction;

import com.homs4j.expendlr.app.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Value
@Builder
public class PutTransaction {
    @NotBlank
    String title;
    @NotBlank
    BigDecimal amount;
    @NotBlank
    Timestamp date;
    @NotBlank
    TransactionType type;
}
