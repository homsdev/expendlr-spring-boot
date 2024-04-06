package com.homs4j.expendlr.app.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PostCategory {
    @NotBlank(message = "Title cannot be empty")
    String title;
    @NotBlank(message = "Color cannot be empty")
    String color;
}
