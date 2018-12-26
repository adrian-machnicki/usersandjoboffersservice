package com.machnickiadrian.usersandjoboffersservice.user.mapper;

import com.machnickiadrian.usersandjoboffersservice.user.User;
import com.machnickiadrian.usersandjoboffersservice.user.UserDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    @Synchronized
    @Override
    public User convert(UserDto source) {
        if (source == null) {
            throw new IllegalArgumentException("UserDto was null");
        }

        final User user = new User();
        user.setId(source.getId());
        user.setLogin(source.getLogin());
        user.setPassword(source.getPassword());
        user.setCreationDate(source.getCreationDate());
        return user;
    }
}
