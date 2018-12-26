package com.machnickiadrian.usersandjoboffersservice.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    private String password;
    private ZonedDateTime creationDate;

    public static UserDto of(Long id, String login, String password, ZonedDateTime creationDate) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setLogin(login);
        userDto.setPassword(password);
        userDto.setCreationDate(creationDate);
        return userDto;
    }

    public static UserDto of(String login, String password, ZonedDateTime creationDate) {
        return of(null, login, password, creationDate);
    }

    public static UserDto of(String login, String password) {
        return of(null, login, password, null);
    }

}
