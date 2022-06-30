package com.shop.account.controller;

import com.shop.account.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public interface UserController {

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds Person by id", response = UserDto.class)
    ResponseEntity<UserDto> get(@PathVariable Long id);

    @PostMapping
    @ApiOperation(value = "Add new Person", response = UserDto.class)
    ResponseEntity<UserDto> add(@RequestBody UserDto personDto);

    @PutMapping
    @ApiOperation(value = "Update Person")
    ResponseEntity<UserDto> update(@RequestBody UserDto personDto);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Person")
    ResponseEntity delete(@PathVariable Long id);
}
