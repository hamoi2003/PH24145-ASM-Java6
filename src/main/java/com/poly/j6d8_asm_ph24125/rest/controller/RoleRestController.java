package com.poly.j6d8_asm_ph24125.rest.controller;

import com.poly.j6d8_asm_ph24125.entity.Role;
import com.poly.j6d8_asm_ph24125.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {
    @Autowired
    RoleService roleService;

    @GetMapping
    public List<Role> getAll() {
        return roleService.findAll();
    }
}
