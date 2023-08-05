package com.poly.j6d8_asm_ph24125.service;

import com.poly.j6d8_asm_ph24125.entity.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findAll();

    List<Authority> findAuthoritiesOfAdministrators();

    Authority create(Authority authority);

    void delete(Integer id);
}
