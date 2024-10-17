package com.maya.accountService.service;

import com.maya.accountService.model.Account;
import com.maya.accountService.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(String username, String password, String fullname) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(hashPassword(password));
        account.setFullname(fullname);
        return accountRepository.save(account);
    }

    public Account getAccount(String username) {
        return accountRepository.findByUsername(username).orElse(null);
    }

    private String hashPassword(String password) {
        return password;
    }

}
