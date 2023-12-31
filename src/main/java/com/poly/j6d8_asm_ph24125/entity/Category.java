package com.poly.j6d8_asm_ph24125.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "Categories")
@Entity
public class Category {
    @Id
    private String id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    List<Product> products;
}
