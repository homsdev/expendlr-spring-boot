package com.homs4j.expendlr.app.dto.category;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PostCategory {
    String title;
    String color;
}
