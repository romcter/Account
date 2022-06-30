package com.shop.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.account.dto.UserDto;
import com.shop.account.exception.GlobalExceptionHandler;
import com.shop.account.exception.ResourceNotFoundException;
import com.shop.account.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static com.shop.account.Util.JSON_DEFAULT_USER_DTO;
import static com.shop.account.Util.DEFAULT_USER_DTO;
import static com.shop.account.Util.ERROR_RESPONSE;
import static com.shop.account.Util.ERROR_MESSAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerImplTest {

    private MockMvc mvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserControllerImpl userControllerImpl;

    public UserControllerImplTest() throws IOException {
    }

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(userControllerImpl)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void getById() throws Exception {
        given(userService.getUser(2L))
                .willReturn(DEFAULT_USER_DTO);

        MockHttpServletResponse response = mvc.perform(
                        get("/api/person/2")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        verify(userService).getUser(any(Long.class));

        assertThat(response.getContentAsString()).isEqualTo(JSON_DEFAULT_USER_DTO);
    }

    @Test
    public void getByNonExistingId() throws Exception {
        when(userService.getUser(2L))
                .thenThrow(new ResourceNotFoundException(ERROR_MESSAGE));

        mvc.perform(get("/api/person/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).isEqualTo(ERROR_RESPONSE));
    }

    @Test
    public void postPerson() throws Exception {
        given(userService.addUser(DEFAULT_USER_DTO))
                .willReturn(DEFAULT_USER_DTO);

        MockHttpServletResponse response = mvc.perform(post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JSON_DEFAULT_USER_DTO))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        verify(userService).addUser(any(UserDto.class));
        assertThat(response.getContentAsString()).isEqualTo(JSON_DEFAULT_USER_DTO);
    }

    @Test
    public void update() throws Exception {
        given(userService.updateUser(DEFAULT_USER_DTO))
                .willReturn(DEFAULT_USER_DTO);

        MockHttpServletResponse response = mvc.perform(
                        put("/api/person")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(JSON_DEFAULT_USER_DTO))
                .andExpect(status().isNoContent())
                .andReturn().getResponse();

        verify(userService).updateUser(any(UserDto.class));
        assertThat(response.getContentAsString()).isEqualTo(JSON_DEFAULT_USER_DTO);
    }
//            PersonDto existing = new PersonDto(3L, "test@test.com", 13L);
//        PersonDto replacement = new PersonDto(3L, "chest@test.com", 12L);
//        PersonEntity replacementEntity = PersonMapper.INSTANCE.personDtoToPerson(replacement);
//        when(personRepository.existsById(3L)).thenReturn(true);
//        ResponseEntity response = personController.update(replacement);
//        verify(personRepository).save(any(PersonEntity.class));


    @Test
    public void deletePersonById() throws Exception {
        mvc.perform(
                        delete("/api/person/2")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(userService).deleteUser(any(Long.class));
    }

}
