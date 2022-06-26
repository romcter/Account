package com.shop.flowerShop.dto;

import com.shop.flowerShop.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDto userToUserDto(UserEntity car);
    UserEntity userDtoToUser(UserDto car);

}