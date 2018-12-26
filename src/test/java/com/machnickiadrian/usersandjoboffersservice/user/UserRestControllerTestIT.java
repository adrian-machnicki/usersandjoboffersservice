package com.machnickiadrian.usersandjoboffersservice.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machnickiadrian.usersandjoboffersservice.user.exception.UserLoginNotUniqueException;
import com.machnickiadrian.usersandjoboffersservice.user.exception.UserNotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;

import static com.machnickiadrian.usersandjoboffersservice.GenerateUtils.getCreationTime;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTestIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUserTest() throws Exception {
        Long userId = 1L;
        UserDto user = UserDto.of(userId, "user", "password", getCreationTime());
        given(userService.findById(userId)).willReturn(user);

        mvc.perform(get("/api/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login", is(user.getLogin())))
                .andExpect(jsonPath("$.password", is(user.getPassword())));
    }

    @Test
    public void getUser_whenUserNotExistsTest() throws Exception {
        Long userId = 1L;
        given(userService.findById(userId)).willThrow(new UserNotFoundException(userId));

        mvc.perform(get("/api/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("User with id 1 does not exist")));
    }

    @Test
    @Ignore
    public void createUserTest() throws Exception {
        UserDto userToSave = UserDto.of("user", "password");
        UserDto savedUser = UserDto.of(1L, "user", "password", ZonedDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String bodyJson = mapper.writeValueAsString(userToSave);

        given(userService.create(userToSave)).willReturn(savedUser);

        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.login", is(userToSave.getLogin())))
                .andExpect(jsonPath("$.password", is(userToSave.getPassword())));
    }

    @Test
    @Ignore
    public void createUser_whenLoginNotUniqueTest() throws Exception {
        UserDto userToSave = UserDto.of("user", "password", getCreationTime());

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String bodyJson = mapper.writeValueAsString(userToSave);

        given(userService.create(userToSave)).willThrow(new UserLoginNotUniqueException(userToSave.getLogin()));

        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("User with login " + userToSave.getLogin() + " already exists")));
    }

}