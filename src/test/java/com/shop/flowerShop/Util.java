package com.shop.flowerShop;

import com.shop.flowerShop.dto.UserDto;

public class Util {

    public static final UserDto DEFAULT_USER_DTO = new UserDto(2L, "test@test.com", "Test", "Testovich", 21L);
    public static final String JSON_DEFAULT_USER_DTO = "{\"id\":2,\"email\":\"test@test.com\",\"name\":\"Test\",\"lastName\":\"Testovich\",\"age\":21}";

    public static final String ERROR_MESSAGE = "Unable to find com.example.talendapp.data.PersonEntity with id 2";
    public static final String ERROR_RESPONSE = "{\"status\":404,\"message\":\"Unable to find com.example.talendapp.data.PersonEntity with id 2\"}";
}
