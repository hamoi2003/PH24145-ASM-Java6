package com.poly.j6d8_asm_ph24125.rest.controller;

import com.poly.j6d8_asm_ph24125.entity.Account;
import com.poly.j6d8_asm_ph24125.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
    @Autowired
    AccountService accountService;

    @GetMapping
    public List<Account> getAccounts(@RequestParam("admin")Optional<Boolean> admin) {
        if(admin.orElse(false)) {
            return accountService.getAdministrators();
        }
        return accountService.findAll();
    }
}
