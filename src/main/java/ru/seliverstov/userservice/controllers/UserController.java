package ru.seliverstov.userservice.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.seliverstov.userservice.dto.UserRegistrationRq;
import ru.seliverstov.userservice.dto.UserRs;
import ru.seliverstov.userservice.dto.UserUpdateRq;
import ru.seliverstov.userservice.services.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/v1/users")
    public final UserRs postUser(@RequestBody final UserRegistrationRq userRegistrationRq) {
        final UserRs userRs = userService.postUser(userRegistrationRq);
        return userRs;
    }

    @GetMapping("api/v1/users")
    public UserRs getUser(@RequestParam final Long id) {
        final UserRs userRs = userService.getUser(id);
        return userRs;
    }

    @DeleteMapping("api/v1/users")
    public void deleteUser(@RequestParam final Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("api/v1/users")
    public UserRs updateUser(@RequestBody final UserUpdateRq userUpdateRq) {
        final UserRs userRs = userService.updateUser(userUpdateRq);
        return userRs;
    }
}

