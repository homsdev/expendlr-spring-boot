package com.homs4j.expendlr.app.mapper.dtomapper;

import com.homs4j.expendlr.app.dto.transaction.PostTransaction;
import com.homs4j.expendlr.app.dto.transaction.PutTransaction;
import com.homs4j.expendlr.app.dto.transaction.TransactionDTO;
import com.homs4j.expendlr.app.model.Account;
import com.homs4j.expendlr.app.model.Category;
import com.homs4j.expendlr.app.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionDTOMapper {

    private final CategoryDTOMapper categoryDTOMapper;

    @Autowired
    public TransactionDTOMapper(CategoryDTOMapper categoryDTOMapper) {
        this.categoryDTOMapper = categoryDTOMapper;
    }

    public TransactionDTO toTransactionDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .transactionId(transaction.getTransactionId())
                .title(transaction.getTitle())
                .type(transaction.getType())
                .date(transaction.getDate())
                .amount(transaction.getAmount())
                .accountId(transaction.getAccount() != null ? transaction.getAccount().getAccountId() : null)
                .categoryDTO(transaction.getCategory() != null ? categoryDTOMapper.toDTO(transaction.getCategory()) : null)
                .status(transaction.getStatus())
                .build();
    }

    public Transaction toTransaction(PutTransaction dto) {
        return Transaction.builder()
                .title(dto.getTitle())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .type(dto.getType())
                .build();
    }

    public Transaction toTransaction(PostTransaction dto) {
        return Transaction.builder()
                .title(dto.getTitle())
                .type(dto.getType())
                .date(dto.getDate())
                .amount(dto.getAmount())
                .account(Account.builder().accountId(dto.getAccountId()).build())
                .category(Category.builder().categoryId(dto.getCategoryId()).build())
                .status(dto.getStatus())
                .build();
    }

}
