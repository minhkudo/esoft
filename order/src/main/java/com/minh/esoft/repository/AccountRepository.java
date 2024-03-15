package com.minh.esoft.repository;

import com.minh.esoft.common.enums.AccountStatusEnum;
import com.minh.esoft.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findAccountEntityByUsername(String userName);

    AccountEntity findAccountEntityByUsernameAndStatus(String userName, AccountStatusEnum accountStatusEnum);
}
