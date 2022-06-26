package com.shop.flowerShop.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @ApiModelProperty(notes = "The unique id of the user", example = "13", required = true)
    private Long id;
    @ApiModelProperty(notes = "The unique email of the user", example = "test@gmail.com", required = true)
    private String email;
    @ApiModelProperty(notes = "The name of the user", example = "John")
    private String name;
    @ApiModelProperty(notes = "The lastname of the user", example = "Galt")
    private String lastName;
    @ApiModelProperty(notes = "The age of the user", example = "19")
    private Long age;
}
