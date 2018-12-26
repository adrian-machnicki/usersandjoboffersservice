package com.machnickiadrian.usersandjoboffersservice.user.mapper;

import com.machnickiadrian.usersandjoboffersservice.user.User;
import com.machnickiadrian.usersandjoboffersservice.user.UserDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Synchronized
    @Override
    public UserDto convert(User source) {
        if (source == null) {
            throw new IllegalArgumentException("User was null");
        }

        final UserDto userDto = new UserDto();
        userDto.setId(source.getId());
        userDto.setLogin(source.getLogin());
        userDto.setPassword(source.getPassword());
        userDto.setCreationDate(source.getCreationDate());
        return userDto;
    }

}
