package com.shop.account.controller;

import com.shop.account.dto.UserDto;
import com.shop.account.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    public ResponseEntity<UserDto> get(Long id) {
        UserDto person = userService.getUser(id);
        log.info("Returned person with id: {}", id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    public ResponseEntity<UserDto> add(UserDto personDto) {
        UserDto person = userService.addUser(personDto);
        log.info("Person {} successfully added", person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    public ResponseEntity<UserDto> update(UserDto personDto) {
        UserDto person = userService.updateUser(personDto);
        log.info("Person {} successfully update", person);
        return new ResponseEntity<>(person, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity delete(Long id) {
        userService.deleteUser(id);
        log.debug("Person with id: {} successfully deleted", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
