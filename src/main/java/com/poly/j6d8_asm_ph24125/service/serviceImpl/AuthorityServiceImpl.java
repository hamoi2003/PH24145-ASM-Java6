package com.poly.j6d8_asm_ph24125.service.serviceImpl;

import com.poly.j6d8_asm_ph24125.dao.AccountDAO;
import com.poly.j6d8_asm_ph24125.dao.AuthorityDAO;
import com.poly.j6d8_asm_ph24125.entity.Account;
import com.poly.j6d8_asm_ph24125.entity.Authority;
import com.poly.j6d8_asm_ph24125.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityDAO authorityDAO;
    @Autowired
    AccountDAO accountDAO;

    @Override
    public List<Authority> findAll() {
        return authorityDAO.findAll();
    }

    @Override
    public List<Authority> findAuthoritiesOfAdministrators() {
        List<Account> accountList = accountDAO.getAdministrators();
        return authorityDAO.authoritiesOf(accountList);
    }

    @Override
    public Authority create(Authority authority) {
        return authorityDAO.save(authority);
    }

    @Override
    public void delete(Integer id) {
        authorityDAO.deleteById(id);
    }


}
