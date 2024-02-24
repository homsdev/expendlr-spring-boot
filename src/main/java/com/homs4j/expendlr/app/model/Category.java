package com.homs4j.expendlr.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    private String categoryId;
    private String title;
    private String color;
}
