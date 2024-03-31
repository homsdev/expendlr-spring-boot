package com.homs4j.expendlr.app.mapper.rowmappers;

import com.homs4j.expendlr.app.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .userId(rs.getString("user_id"))
                .name(rs.getString("name"))
                .lastName(rs.getString("last_name"))
                .username(rs.getString("username"))
                .email(rs.getString("email"))
                .phoneNumber(rs.getString("phone"))
                .password(rs.getString("password"))
                .build();
    }
}
