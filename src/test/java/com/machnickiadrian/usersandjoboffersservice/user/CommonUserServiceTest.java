package com.machnickiadrian.usersandjoboffersservice.user;

import com.machnickiadrian.usersandjoboffersservice.UsersandjoboffersserviceApplication;
import com.machnickiadrian.usersandjoboffersservice.user.exception.UserLoginNotUniqueException;
import com.machnickiadrian.usersandjoboffersservice.user.exception.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

import static com.machnickiadrian.usersandjoboffersservice.GenerateUtils.getCreationTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UsersandjoboffersserviceApplication.class})
public class CommonUserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void createAndFindByIdTest() {
        // given
        ZonedDateTime creationDateTime = getCreationTime();
        UserDto userToSave = UserDto.of("user1", "password", creationDateTime);

        // when
        UserDto savedUser = userService.create(userToSave);
        UserDto foundUser = userService.findById(savedUser.getId());

        // then
        assertThat(foundUser.getId()).isEqualTo(savedUser.getId());
        assertThat(foundUser.getLogin())
                .isEqualTo(savedUser.getLogin())
                .isEqualTo(userToSave.getLogin());

        assertThat(foundUser.getPassword())
                .isEqualTo(savedUser.getPassword())
                .isEqualTo(userToSave.getPassword());

        assertThat(foundUser.getCreationDate())
                .isEqualTo(savedUser.getCreationDate())
                .isEqualTo(userToSave.getCreationDate());
    }

    @Test
    public void createTest_whenLoginNotUnique() {
        // given
        ZonedDateTime creationDateTime = getCreationTime();
        UserDto user = UserDto.of("user2", "password", creationDateTime);
        userService.create(user);

        // when
        // then
        assertThatThrownBy(() -> userService.create(user))
                .isInstanceOf(UserLoginNotUniqueException.class)
                .hasFieldOrPropertyWithValue("login", user.getLogin());
    }

    @Test
    public void findByIdTest_whenUserDoesNotExist() {
        assertThatThrownBy(() -> userService.findById(99L))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void deleteByIdTest() {
        // given
        ZonedDateTime creationDateTime = getCreationTime();
        UserDto userDto = UserDto.of("user3", "password", creationDateTime);
        UserDto savedUser = userService.create(userDto);
        Long userId = savedUser.getId();

        // when
        boolean userDeleted = userService.deleteById(userId);

        // then
        assertThat(userDeleted).isTrue();
        assertThatThrownBy(() -> userService.findById(userId))
                .isInstanceOf(UserNotFoundException.class)
                .hasFieldOrPropertyWithValue("userId", userId);
    }


}