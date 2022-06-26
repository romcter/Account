package com.shop.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode
//@Table(name = "user")
public class UserEntity {

    @Id
    private Long id;
    private String email;
    private String name;
    private String lastName;
    private Long age;
}
