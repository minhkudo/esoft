package com.minh.esoft.service;

import com.minh.esoft.common.enums.AccountStatusEnum;
import com.minh.esoft.common.exception.DataAlreadyExistsException;
import com.minh.esoft.repository.AccountRepository;
import com.minh.esoft.repository.entity.AccountEntity;
import com.minh.esoft.repository.request.UserRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountEntity createAccount(UserRegisterRequest userRegisterRequest) throws DataAlreadyExistsException {
        AccountEntity accountEntity = accountRepository.findAccountEntityByUsernameAndStatus(userRegisterRequest.getUsername(), AccountStatusEnum.ACTIVE);
        if (accountEntity != null) {
            throw new DataAlreadyExistsException();
        }
        accountEntity = new AccountEntity();
        accountEntity.setUsername(userRegisterRequest.getUsername());
        accountEntity.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        accountEntity.setStatus(AccountStatusEnum.ACTIVE);
        accountEntity.setRole(userRegisterRequest.getRole());

        return accountRepository.save(accountEntity);
    }
}
