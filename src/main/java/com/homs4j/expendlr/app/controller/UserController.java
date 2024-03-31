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
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserDTO> postUser(@RequestBody PostUser user) {
        UserDTO savedUser = userService.saveUser(user);
        return savedUser != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(savedUser) :
                ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") String id, @RequestBody PutUser user) {
        UserDTO updatedUser = userService.updateUser(user, id);
        return updatedUser != null ?
                ResponseEntity.status(HttpStatus.OK).body(updatedUser) :
                ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        int result = userService.deleteUser(id);
        return result > 0 ? ResponseEntity.ok("User deleted") : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{userId}/account")
    public ResponseEntity<List<AccountDTO>> getAllByUserId(@PathVariable("userId") String userId) {
        List<AccountDTO> userAccounts = accountService.getAllByUserId(userId);
        return userAccounts.isEmpty() ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.ok(userAccounts);
    }

}
