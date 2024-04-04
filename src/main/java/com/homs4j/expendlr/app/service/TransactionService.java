package com.homs4j.expendlr.app.service;

import com.homs4j.expendlr.app.dto.transaction.PostTransaction;
import com.homs4j.expendlr.app.dto.transaction.PutTransaction;
import com.homs4j.expendlr.app.dto.transaction.TransactionDTO;
import com.homs4j.expendlr.app.enums.TransactionStatus;
import com.homs4j.expendlr.app.enums.TransactionType;
import com.homs4j.expendlr.app.exception.EntityNotCreatedException;
import com.homs4j.expendlr.app.exception.EntityNotFoundException;
import com.homs4j.expendlr.app.exception.EntityNotUpdatedException;
import com.homs4j.expendlr.app.mapper.dtomapper.TransactionDTOMapper;
import com.homs4j.expendlr.app.model.Account;
import com.homs4j.expendlr.app.model.Category;
import com.homs4j.expendlr.app.model.Transaction;
import com.homs4j.expendlr.app.repository.AccountRepository;
import com.homs4j.expendlr.app.repository.CategoryRepository;
import com.homs4j.expendlr.app.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionDTOMapper transactionDTOMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, CategoryRepository categoryRepository, TransactionDTOMapper transactionDTOMapper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.transactionDTOMapper = transactionDTOMapper;
    }

    public TransactionDTO save(PostTransaction dto) {
        Transaction transactionToSave = transactionDTOMapper.toTransaction(dto);
        transactionToSave.setTransactionId(UUID.randomUUID().toString());

        if (transactionToSave.getStatus().equals(TransactionStatus.PAID)) {
            Account accountToUpdate = accountRepository.findById(dto.getAccountId())
                    .orElseThrow(() -> new EntityNotFoundException("Account related to transaction not found. Failed to update balance"));

            BigDecimal currentBalance = accountToUpdate.getBalance();
            accountToUpdate.setBalance(
                    dto.getType().equals(TransactionType.INCOME) ?
                            currentBalance.add(dto.getAmount())
                            : currentBalance.subtract(dto.getAmount())
            );

            accountRepository.update(accountToUpdate)
                    .orElseThrow(() -> new EntityNotUpdatedException("Failed to update account information"));
        }

        Optional<Transaction> saved = transactionRepository.save(transactionToSave);
        return saved.map(transactionDTOMapper::toTransactionDTO)
                .orElseThrow(() -> new EntityNotCreatedException("Failed to save transaction information"));
    }

    public TransactionDTO update(PutTransaction dto, String transactionId) {
        Transaction transactionToUpdate = transactionDTOMapper.toTransaction(dto);
        transactionToUpdate.setTransactionId(transactionId);
        Transaction currentTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(()-> new EntityNotFoundException("Transaction to update not found"));

        Account account = accountRepository.findById(currentTransaction.getAccount().getAccountId())
                .orElseThrow(()->new EntityNotFoundException("Account related to transaction not found"));
        BigDecimal accountBalance = account.getBalance();

        if (currentTransaction.getStatus().equals(TransactionStatus.PAID)) {
            accountBalance = currentTransaction.getType().equals(TransactionType.EXPENSE) ?
                    accountBalance.add(currentTransaction.getAmount()) :
                    accountBalance.subtract(currentTransaction.getAmount());

            accountBalance = transactionToUpdate.getType().equals(TransactionType.EXPENSE) ?
                    accountBalance.subtract(transactionToUpdate.getAmount()) :
                    accountBalance.add(transactionToUpdate.getAmount());
            account.setBalance(accountBalance);

            accountRepository.update(account)
                    .orElseThrow(()-> new EntityNotUpdatedException("Failed to update account balance"));
        }

        return transactionRepository.update(transactionToUpdate)
                .map(transactionDTOMapper::toTransactionDTO)
                .orElseThrow(() -> new EntityNotUpdatedException("Failed to update transaction information"));
    }

    public List<TransactionDTO> findAllByUserAndMonth(Timestamp start, Timestamp end, String accountId) {
        List<Transaction> allByDate = transactionRepository.findAllByDate(start, end, accountId);
        if (allByDate.isEmpty()) throw new EntityNotFoundException("No transaction information was found for the given parameters");

        allByDate.forEach(transaction -> {
            Category category = categoryRepository.findById(transaction.getCategory().getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category associated with transaction not found"));
            transaction.setCategory(category);
        });
        return allByDate.stream().map(transactionDTOMapper::toTransactionDTO).collect(Collectors.toList());
    }

    public TransactionDTO findById(String id) {
        return transactionRepository.findById(id)
                .map(transactionDTOMapper::toTransactionDTO)
                .orElseThrow(() -> new EntityNotFoundException("Transaction information not found"));
    }

    public int deleteById(String transactionId) {
        int result = transactionRepository.deleteById(transactionId);
        if (result <= 0) throw new EntityNotFoundException("Failed to delete transaction");
        return result;
    }

    public int updateTransactionStatus(String transactionId, TransactionStatus status) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction to update not found"));

        if (transaction.getStatus().equals(status)) {
            throw new EntityNotUpdatedException("Transaction status has not changed");
        }

        Account account = accountRepository.findById(transaction.getAccount().getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account related to transaction not found"));

        BigDecimal adjustedAccountBalance = getAdjustedBalance(status, account, transaction);
        account.setBalance(adjustedAccountBalance);
        accountRepository.update(account)
                .orElseThrow(() -> new EntityNotUpdatedException("Failed to update account balance"));

        int result = transactionRepository.updateTransactionStatus(transactionId, status);

        if (result <= 0) throw new EntityNotUpdatedException("Failed to update transaction status");

        return result;
    }

    private static BigDecimal getAdjustedBalance(TransactionStatus status, Account account, Transaction transaction) {
        BigDecimal adjustedAccountBalance = account.getBalance();
        if (transaction.getType() == TransactionType.INCOME) {
            adjustedAccountBalance = status.equals(TransactionStatus.PAID) ?
                    account.getBalance().add(transaction.getAmount()) :
                    account.getBalance().subtract(transaction.getAmount());
        }
        if (transaction.getType() == TransactionType.EXPENSE) {
            adjustedAccountBalance = status.equals(TransactionStatus.PAID) ?
                    account.getBalance().subtract(transaction.getAmount()) :
                    account.getBalance().add(transaction.getAmount());
        }
        return adjustedAccountBalance;
    }

}
