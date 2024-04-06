package com.homs4j.expendlr.app.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PutAccount {
    @NotBlank
    String alias;
    @NotBlank
    BigDecimal balance;
}
