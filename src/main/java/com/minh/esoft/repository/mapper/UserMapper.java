package com.minh.esoft.repository.mapper;

import com.minh.esoft.repository.dtos.UserDto;
import com.minh.esoft.repository.entity.AccountEntity;
import com.minh.esoft.repository.entity.UserEntity;
import com.minh.esoft.repository.request.UserRegisterRequest;
import com.minh.esoft.repository.response.UserRegisterResponse;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapEntity2Dto(UserEntity userEntity);

    UserRegisterResponse mapEntityAndAccountEntity2UserRegisterResponse(UserEntity userEntity, AccountEntity accountEntity);

    @Mapping(source = "accountEntity.id", target = "accountId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    UserEntity mapUserRegisterRequestAndAccountEntity2Entity(UserRegisterRequest userRegisterRequest, AccountEntity accountEntity);
}
