package com.machnickiadrian.usersandjoboffersservice.user;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Log
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<Collection<UserDto>> findAll() {
        Collection<UserDto> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findUser(@PathVariable Long id) {
        UserDto user = userService.findById(id);
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> createUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ExceptionControllerAdvice.ValidationErrorInfo errorInfo =
                    new ExceptionControllerAdvice.ValidationErrorInfo(bindingResult.getAllErrors().toString());
            return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
        }

        UserDto savedUser = userService.create(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ExceptionControllerAdvice.ValidationErrorInfo errorInfo =
                    new ExceptionControllerAdvice.ValidationErrorInfo(bindingResult.getAllErrors().toString());
            return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
        }

        UserDto updatedUser = userService.update(userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}