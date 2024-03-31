package com.homs4j.expendlr.app.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PostUser {
    String name;
    String lastName;
    String username;
    String email;
    String phone;
    String password;
}
