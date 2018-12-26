package com.machnickiadrian.usersandjoboffersservice.user;

import com.machnickiadrian.usersandjoboffersservice.user.exception.UserLoginNotUniqueException;
import com.machnickiadrian.usersandjoboffersservice.user.exception.UserNotFoundException;
import com.machnickiadrian.usersandjoboffersservice.user.mapper.UserDtoToUserConverter;
import com.machnickiadrian.usersandjoboffersservice.user.mapper.UserToUserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonUserService implements UserService {

    private UserRepository userRepository;
    private UserToUserDtoConverter userToUserDtoConverter;
    private UserDtoToUserConverter userDtoToUserConverter;

    @Autowired
    public CommonUserService(UserRepository userRepository, UserToUserDtoConverter userToUserDtoConverter, UserDtoToUserConverter userDtoToUserConverter) {
        this.userRepository = userRepository;
        this.userToUserDtoConverter = userToUserDtoConverter;
        this.userDtoToUserConverter = userDtoToUserConverter;
    }

    @Override
    public UserDto findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User's id cannot be null");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userToUserDtoConverter.convert(user);
    }

    @Override
    public UserDto create(UserDto userDto) {
        if (userRepository.existsByLogin(userDto.getLogin())) {
            throw new UserLoginNotUniqueException(userDto.getLogin());
        }

        User userToSave = userDtoToUserConverter.convert(userDto);
        userToSave = userRepository.save(userToSave);
        return userToUserDtoConverter.convert(userToSave);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User userToSave = userDtoToUserConverter.convert(userDto);
        userToSave = userRepository.save(userToSave);
        return userToUserDtoConverter.convert(userToSave);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }
}
