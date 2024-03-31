package com.homs4j.expendlr.app.service;

import com.homs4j.expendlr.app.dto.transaction.PostTransaction;
import com.homs4j.expendlr.app.dto.transaction.PutTransaction;
import com.homs4j.expendlr.app.dto.transaction.TransactionDTO;
import com.homs4j.expendlr.app.enums.TransactionStatus;
import com.homs4j.expendlr.app.enums.TransactionType;
import com.homs4j.expendlr.app.mapper.dtomapper.TransactionDTOMapper;
import com.homs4j.expendlr.app.model.Account;
import com.homs4j.expendlr.app.model.Transaction;
import com.homs4j.expendlr.app.repository.AccountRepository;
import com.homs4j.expendlr.app.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionDTOMapper transactionDTOMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, TransactionDTOMapper transactionDTOMapper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionDTOMapper = transactionDTOMapper;
    }

    public TransactionDTO save(PostTransaction dto) {
        Transaction transactionToSave = transactionDTOMapper.toTransaction(dto);
        transactionToSave.setTransactionId(UUID.randomUUID().toString());

        if (transactionToSave.getStatus().equals(TransactionStatus.PAID)) {
            Optional<Account> accountToUpdate = accountRepository.findById(dto.getAccountId());
            accountToUpdate.ifPresent((account -> {
                BigDecimal currentBalance = account.getBalance();
                account.setBalance(
                        dto.getType().equals(TransactionType.INCOME) ?
                                currentBalance.add(dto.getAmount())
                                : currentBalance.subtract(dto.getAmount())
                );

                accountRepository.update(account);
            }));
        }

        Optional<Transaction> saved = transactionRepository.save(transactionToSave);
        return saved.map(transactionDTOMapper::toTransactionDTO).orElse(null);
    }

    public TransactionDTO update(PutTransaction dto, String transactionId) {
        Transaction transactionToUpdate = transactionDTOMapper.toTransaction(dto);
        transactionToUpdate.setTransactionId(transactionId);
        Transaction currentTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(NullPointerException::new);

        Account account = accountRepository.findById(currentTransaction.getAccount().getAccountId())
                .orElseThrow(NullPointerException::new);
        BigDecimal accountBalance = account.getBalance();

        if (currentTransaction.getStatus().equals(TransactionStatus.PAID)) {
            accountBalance = currentTransaction.getType().equals(TransactionType.EXPENSE) ?
                    accountBalance.add(currentTransaction.getAmount()):
                    accountBalance.subtract(currentTransaction.getAmount());

            accountBalance = transactionToUpdate.getType().equals(TransactionType.EXPENSE) ?
                    accountBalance.subtract(transactionToUpdate.getAmount()):
                    accountBalance.add(transactionToUpdate.getAmount());
            account.setBalance(accountBalance);
            accountRepository.update(account);
        }
        return transactionRepository.update(transactionToUpdate).map(transactionDTOMapper::toTransactionDTO).orElse(null);
    }

    public List<TransactionDTO> findAllByUserAndMonth(Timestamp start, Timestamp end, String accountId) {
        //TODO:Retrieve all category information
        List<Transaction> allByDate = transactionRepository.findAllByDate(start, end, accountId);
        return allByDate.stream().map(transactionDTOMapper::toTransactionDTO).collect(Collectors.toList());
    }

    public TransactionDTO findById(String id) {
        return transactionRepository.findById(id).map(transactionDTOMapper::toTransactionDTO).orElse(null);
    }

    //TODO: Implement transaction deletion

}
