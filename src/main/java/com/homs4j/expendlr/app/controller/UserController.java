package com.homs4j.expendlr.app.controller;

import com.homs4j.expendlr.app.dto.account.AccountDTO;
import com.homs4j.expendlr.app.dto.user.PostUser;
import com.homs4j.expendlr.app.dto.user.PutUser;
import com.homs4j.expendlr.app.dto.user.UserDTO;
import com.homs4j.expendlr.app.service.AccountService;
import com.homs4j.expendlr.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AccountService accountService;


    public UserController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String id) {
        UserDTO result = userService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserDTO> postUser(@RequestBody PostUser user) {
        UserDTO savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") String id, @RequestBody PutUser user) {
        UserDTO updatedUser = userService.updateUser(user, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        int result = userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/{userId}/account")
    public ResponseEntity<List<AccountDTO>> getAllByUserId(@PathVariable("userId") String userId) {
        List<AccountDTO> userAccounts = accountService.getAllByUserId(userId);
        return ResponseEntity.ok(userAccounts);
    }

}
