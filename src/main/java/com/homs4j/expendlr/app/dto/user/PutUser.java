package com.homs4j.expendlr.app.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PutUser {
    @NotBlank
    String name;
    @NotBlank
    String lastname;
    @NotBlank
    String username;
    @NotBlank
    String email;
    @NotBlank
    String phone;
}
