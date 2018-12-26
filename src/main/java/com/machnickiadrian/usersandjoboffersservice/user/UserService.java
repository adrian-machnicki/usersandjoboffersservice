package com.machnickiadrian.usersandjoboffersservice.user;

import java.util.Collection;

public interface UserService {

    Collection<UserDto> findAll();
    UserDto findById(Long id);
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    boolean deleteById(Long id);

}
