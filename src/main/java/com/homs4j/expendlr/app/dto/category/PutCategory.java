package com.homs4j.expendlr.app.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PutCategory {
    @NotBlank
    String title;
    @NotBlank
    String color;
}
