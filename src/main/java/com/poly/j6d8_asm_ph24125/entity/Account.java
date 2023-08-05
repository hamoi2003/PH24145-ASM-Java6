package com.poly.j6d8_asm_ph24125.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @Column(name = "Username")
    @NotBlank
    private String username;
    @Column(name = "Password")
    @NotBlank
    private String password;
    @Column(name = "Fullname")
    @NotBlank
    private String fullname;
    @Column(name = "Email")
    @NotBlank
    private String email;
    @Column(name = "Photo")
    @NotBlank
    private String photo;
    @Column(name = "Activated")
    @NotNull
    private Boolean activated=true;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    List<Authority> authorities;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Order> orders;

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", activated=" + activated +
                '}';
    }
}
