package com.shop.flowerShop.controller;

import com.shop.flowerShop.dto.UserDto;
import com.shop.flowerShop.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds Person by id", response = UserDto.class)
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        UserDto person = userService.getUser(id);
        log.info("Returned person with id: {}", id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Add new Person", response = UserDto.class)
    public ResponseEntity<UserDto> add(@RequestBody UserDto personDto) {
        UserDto person = userService.addUser(personDto);
        log.info("Person {} successfully added", person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Update Person")
    public ResponseEntity<UserDto> update(@RequestBody UserDto personDto) {
        UserDto person = userService.updateUser(personDto);
        log.info("Person {} successfully update", person);
        return new ResponseEntity<>(person, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Person")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.deleteUser(id);
        log.debug("Person with id: {} successfully deleted", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
