package com.homs4j.expendlr.app.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PutUser {
    String name;
    String lastname;
    String username;
    String email;
    String phone;
}
