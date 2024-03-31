package com.homs4j.expendlr.app.dto.account;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PostAccount {
    String alias;
    BigDecimal balance;
    String userId;
}
