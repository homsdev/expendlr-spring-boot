package com.homs4j.expendlr.app.controller;

import com.homs4j.expendlr.app.dto.account.AccountDTO;
import com.homs4j.expendlr.app.dto.account.PostAccount;
import com.homs4j.expendlr.app.dto.account.PutAccount;
import com.homs4j.expendlr.app.dto.transaction.TransactionDTO;
import com.homs4j.expendlr.app.model.Transaction;
import com.homs4j.expendlr.app.service.AccountService;
import com.homs4j.expendlr.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @Autowired
    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> postAccount(@RequestBody PostAccount dto) {
        AccountDTO savedAccount = accountService.saveAccount(dto);
        return ResponseEntity.ok(savedAccount);
    }

    @PutMapping("/{accountId}")
    private ResponseEntity<AccountDTO> putAccount(@PathVariable("accountId") String accountId, @RequestBody PutAccount dto) {
        AccountDTO updatedAccount = accountService.updateAccount(dto, accountId);
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getByAccountId(@PathVariable("id") String accountId) {
        AccountDTO account = accountService.findById(accountId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{accountId}/transaction")
    public ResponseEntity<List<TransactionDTO>> getTransactionFilterByDate(@PathVariable("accountId") String accountId,
                                                                           @RequestParam String startDate,
                                                                           @RequestParam String endDate) {
        List<TransactionDTO> allByUserAndMonth = transactionService.findAllByUserAndMonth(Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), accountId);
        return ResponseEntity.ok(allByUserAndMonth);
    }

}
