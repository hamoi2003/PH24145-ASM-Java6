package com.poly.j6d8_asm_ph24125.dao;

import com.poly.j6d8_asm_ph24125.entity.Account;
import com.poly.j6d8_asm_ph24125.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorityDAO extends JpaRepository<Authority, Integer> {
    @Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
    List<Authority> authoritiesOf(List<Account> accountList);
}
