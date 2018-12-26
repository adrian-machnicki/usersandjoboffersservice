package com.machnickiadrian.usersandjoboffersservice.user;

public interface UserService {

    UserDto findById(Long id);
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    boolean deleteById(Long id);

}
