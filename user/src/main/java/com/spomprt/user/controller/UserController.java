package com.spomprt.user.controller;

import com.spomprt.user.controller.dto.UserCreateDto;
import com.spomprt.user.controller.dto.UserDto;
import com.spomprt.user.controller.dto.UserUpdateDto;
import com.spomprt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        log.info(">> All users");
        return userService.findAll();
    }

    @GetMapping("{id}")
    public UserDto getUser(@PathVariable("id") String id) {
        log.info(">> Retrieve user by id - {}", id);
        return userService.get(id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") String id) {
        log.info(">> Delete user by id - {}", id);
        userService.delete(id);
    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable("id") String id,
                           @RequestBody UserUpdateDto userUpdateDto) {
        log.info(">> Update user by id - {}\nNew attributes - {}", id, userUpdateDto);
        userService.update(id, userUpdateDto);
    }

    @PostMapping
    public void createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        log.info(">> Create user - {}", userCreateDto);
        userService.create(userCreateDto);
    }

}
