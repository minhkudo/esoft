package com.minh.esoft.service;

import com.minh.esoft.repository.UserRepository;
import com.minh.esoft.repository.entity.AccountEntity;
import com.minh.esoft.repository.entity.UserEntity;
import com.minh.esoft.repository.request.UserRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity createUser(UserRegisterRequest userRegisterRequest, AccountEntity accountEntity) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAccountId(accountEntity.getId());
        userEntity.setFirstName(userRegisterRequest.getFirstName());
        userEntity.setLastName(userRegisterRequest.getLastName());

        return userRepository.save(userEntity);
    }
}
