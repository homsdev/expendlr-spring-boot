package com.homs4j.expendlr.app.mapper.rowmappers;

import com.homs4j.expendlr.app.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Category.builder()
                .categoryId(rs.getString("category_id"))
                .title(rs.getString("name"))
                .color(rs.getString("color"))
                .build();
    }
}
