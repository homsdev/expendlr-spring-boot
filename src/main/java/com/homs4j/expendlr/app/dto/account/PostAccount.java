package com.homs4j.expendlr.app.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PostAccount {
    @NotBlank
    String alias;
    @NotBlank
    BigDecimal balance;
    @NotBlank
    String userId;
}
