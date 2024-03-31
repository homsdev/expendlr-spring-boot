package com.homs4j.expendlr.app.dto.category;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryDTO {
    String categoryId;
    String name;
    String color;
}
