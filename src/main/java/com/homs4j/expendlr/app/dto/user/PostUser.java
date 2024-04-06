package com.homs4j.expendlr.app.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PostUser {
    @NotBlank
    String name;
    @NotBlank
    String lastName;
    @NotBlank
    String username;
    @NotBlank @Email
    String email;
    String phone;
    @NotBlank
    String password;
}
