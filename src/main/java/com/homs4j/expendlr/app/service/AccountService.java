package com.homs4j.expendlr.app.service;

import com.homs4j.expendlr.app.dto.account.AccountDTO;
import com.homs4j.expendlr.app.dto.account.PostAccount;
import com.homs4j.expendlr.app.dto.account.PutAccount;
import com.homs4j.expendlr.app.mapper.dtomapper.AccountDTOMapper;
import com.homs4j.expendlr.app.model.Account;
import com.homs4j.expendlr.app.repository.AccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountDTOMapper dtoMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountDTOMapper dtoMapper) {
        this.accountRepository = accountRepository;
        this.dtoMapper = dtoMapper;
    }

    public AccountDTO saveAccount(PostAccount dto) {
        Account account = dtoMapper.toAccount(dto);
        Timestamp createdAt = Timestamp.from(Instant.now());
        account.setAccountId(UUID.randomUUID().toString());
        account.setCreatedOn(createdAt);
        Optional<Account> saved = accountRepository.save(account);
        return saved.map(dtoMapper::toAccountDTO).orElse(null);
    }

    public AccountDTO updateAccount(PutAccount dto,String accountId) {
        Account accountToUpdate = dtoMapper.toAccount(dto);
        accountToUpdate.setAccountId(accountId);
        Optional<Account> updated = accountRepository.update(accountToUpdate);
        return updated.map(dtoMapper::toAccountDTO).orElse(null);
    }

    public List<AccountDTO> getAllByUserId(String userId) {
        List<Account> accountList = accountRepository.findAllByUserId(userId);
        return accountList.stream().map(dtoMapper::toAccountDTO).collect(Collectors.toList());
    }

    public AccountDTO findById(String accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.map(dtoMapper::toAccountDTO).orElse(null);
    }

}
