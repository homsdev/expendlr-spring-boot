package com.homs4j.expendlr.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String userId;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}
