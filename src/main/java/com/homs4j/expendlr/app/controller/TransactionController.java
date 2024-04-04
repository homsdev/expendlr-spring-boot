package com.homs4j.expendlr.app.controller;

import com.homs4j.expendlr.app.dto.transaction.PostTransaction;
import com.homs4j.expendlr.app.dto.transaction.PutTransaction;
import com.homs4j.expendlr.app.dto.transaction.TransactionDTO;
import com.homs4j.expendlr.app.enums.TransactionStatus;
import com.homs4j.expendlr.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> postTransaction(@RequestBody PostTransaction dto) {
        TransactionDTO saved = transactionService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> putTransaction(@PathVariable("transactionId") String transactionId,
                                                         @RequestBody PutTransaction dto) {
        TransactionDTO updated = transactionService.update(dto, transactionId);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("transactionId") String transactionId) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{transactionId}")
    public ResponseEntity<String> patchTransactionStatus(@PathVariable String transactionId, @RequestParam("status") String status) {
        transactionService.updateTransactionStatus(transactionId, TransactionStatus.valueOf(status));
        return ResponseEntity.ok("Transaction status successfully changed");
    }

}
