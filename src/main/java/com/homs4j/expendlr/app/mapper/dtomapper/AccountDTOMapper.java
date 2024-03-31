package com.homs4j.expendlr.app.mapper.dtomapper;

import com.homs4j.expendlr.app.dto.account.AccountDTO;
import com.homs4j.expendlr.app.dto.account.PostAccount;
import com.homs4j.expendlr.app.dto.account.PutAccount;
import com.homs4j.expendlr.app.model.Account;
import com.homs4j.expendlr.app.model.User;
import org.springframework.stereotype.Component;

@Component
public class AccountDTOMapper {

    public AccountDTO toAccountDTO(Account account) {
        return AccountDTO.builder()
                .accountId(account.getAccountId())
                .alias(account.getAlias())
                .balance(account.getBalance())
                .userId(account.getUser() != null ? account.getUser().getUserId() : null)
                .build();
    }

    public Account toAccount(AccountDTO dto) {
        return Account.builder()
                .accountId(dto.getAccountId())
                .alias(dto.getAlias())
                .balance(dto.getBalance())
                .user(User.builder().userId(dto.getUserId()).build())
                .build();
    }

    public Account toAccount(PostAccount dto) {
        return Account.builder()
                .alias(dto.getAlias())
                .balance(dto.getBalance())
                .user(User.builder().userId(dto.getUserId()).build())
                .build();
    }

    public Account toAccount(PutAccount dto) {
        return Account.builder()
                .alias(dto.getAlias())
                .balance(dto.getBalance())
                .build();
    }
}
