package com.maya.accountService.controller;

import com.maya.accountService.model.Account;
import com.maya.accountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        if (account.getUsername() == null || account.getUsername().length() < 3 || account.getUsername().length() > 64) {
            return ResponseEntity.badRequest().body(new ErrorResponse("ACC0001", "Invalid username"));
        }
        if (account.getPassword() == null || account.getPassword().length() < 8 || account.getPassword().length() > 256) {
            return ResponseEntity.badRequest().body(new ErrorResponse("ACC0002", "Invalid password"));
        }
        if (account.getFullname() == null || account.getFullname().length() < 3 || account.getFullname().length() > 256) {
            return ResponseEntity.badRequest().body(new ErrorResponse("ACC0003", "Invalid fullname"));
        }

        Account createdAccount = accountService.createAccount(account.getUsername(), account.getPassword(), account.getFullname());
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getAccount(@PathVariable String username) {
        Account account = accountService.getAccount(username);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("ACC0004", "User not found"));
        }
        return ResponseEntity.ok(account);
    }

    static class ErrorResponse {
        private String code;
        private String message;

        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
