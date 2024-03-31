package com.homs4j.expendlr.app.mapper.rowmappers;

import com.homs4j.expendlr.app.model.Account;
import com.homs4j.expendlr.app.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Account.builder()
                .accountId(rs.getString("account_id"))
                .alias(rs.getString("alias"))
                .balance(rs.getBigDecimal("balance"))
                .createdOn(rs.getTimestamp("created_on"))
                .updatedOn(rs.getTimestamp("updated_on"))
                .user(User.builder().userId(rs.getString("user_id")).build())
                .build();
    }
}
