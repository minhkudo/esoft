package com.minh.esoft.service;

import com.minh.esoft.common.exception.DataAlreadyExistsException;
import com.minh.esoft.repository.entity.AccountEntity;
import com.minh.esoft.repository.entity.UserEntity;
import com.minh.esoft.repository.mapper.AccountUserMapper;
import com.minh.esoft.repository.request.UserRegisterRequest;
import com.minh.esoft.repository.response.UserRegisterResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AccountUserService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AccountService accountService;

    public UserRegisterResponse createAccountUser(UserRegisterRequest userRegisterRequest) throws DataAlreadyExistsException {
        AccountEntity accountEntity = accountService.createAccount(userRegisterRequest);
        UserEntity userEntity = userService.createUser(userRegisterRequest, accountEntity);

        return AccountUserMapper.INSTANCE.map2AccountUserDto(accountEntity, userEntity);
    }
}
