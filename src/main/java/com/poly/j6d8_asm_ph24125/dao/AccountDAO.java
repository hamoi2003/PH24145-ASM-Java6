package com.poly.j6d8_asm_ph24125.dao;

import com.poly.j6d8_asm_ph24125.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDAO extends JpaRepository<Account, String> {
    @Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('DIRE', 'STAF')")
    List<Account> getAdministrators();
}
