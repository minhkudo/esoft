package com.minh.esoft.repository.mapper;

import com.minh.esoft.repository.entity.AccountEntity;
import com.minh.esoft.repository.entity.UserEntity;
import com.minh.esoft.repository.response.UserRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountUserMapper {
    AccountUserMapper INSTANCE = Mappers.getMapper(AccountUserMapper.class);

    UserRegisterResponse map2AccountUserDto(AccountEntity accountEntity, UserEntity userEntity);
}
