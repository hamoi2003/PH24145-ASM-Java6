package com.poly.j6d8_asm_ph24125.service.serviceImpl;

import com.poly.j6d8_asm_ph24125.dao.AccountDAO;
import com.poly.j6d8_asm_ph24125.entity.Account;
import com.poly.j6d8_asm_ph24125.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO accountDAO;

    @Override
    public Account findById(String username) {
        return accountDAO.findById(username).get();
    }

    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public void save(Account account) {
        accountDAO.save(account);
    }

    @Override
    public List<Account> getAdministrators() {
        return accountDAO.getAdministrators();
    }
}
