package com.homs4j.expendlr.app.mapper.rowmappers;

import com.homs4j.expendlr.app.enums.TransactionStatus;
import com.homs4j.expendlr.app.enums.TransactionType;
import com.homs4j.expendlr.app.model.Account;
import com.homs4j.expendlr.app.model.Category;
import com.homs4j.expendlr.app.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Transaction.builder()
                .transactionId(rs.getString("transaction_id"))
                .title(rs.getString("title"))
                .type(TransactionType.valueOf(rs.getString("type")))
                .date(rs.getTimestamp("date"))
                .amount(rs.getBigDecimal("amount"))
                .account(Account.builder().accountId(rs.getString("account_id")).build())
                .category(Category.builder().categoryId(rs.getString("category_id")).build())
                .status(TransactionStatus.valueOf(rs.getString("status")))
                .build();
    }
}
