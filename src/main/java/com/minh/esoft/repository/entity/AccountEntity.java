package com.minh.esoft.repository.entity;

import com.minh.esoft.common.status.AccountStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "accounts")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AccountEntity extends BaseEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private AccountStatusEnum status;
}
