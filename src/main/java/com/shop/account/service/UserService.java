package com.shop.account.service;

import com.shop.account.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto getUser(Long id);
    UserDto addUser(UserDto user);
    UserDto updateUser(UserDto user);
    void deleteUser(Long id);
}
