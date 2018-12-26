package com.machnickiadrian.usersandjoboffersservice.user;

import java.util.Collection;

public interface UserService {

    Collection<UserDto> findAll();
    UserDto findById(Long id);
    UserDto create(UserDto userDto);
    UserDto update(Long id, UserDto userDto);
    boolean deleteById(Long id);
    boolean existsByLogin(String login);
}
