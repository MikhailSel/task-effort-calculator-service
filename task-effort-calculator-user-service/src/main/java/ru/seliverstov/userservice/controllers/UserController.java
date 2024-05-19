package ru.seliverstov.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.seliverstov.userservice.model.dto.UserRegistrationRq;
import ru.seliverstov.userservice.model.dto.UserRs;
import ru.seliverstov.userservice.model.dto.UserUpdateRq;
import ru.seliverstov.userservice.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("api/v1/users")
    public final UserRs postUser(@RequestBody final UserRegistrationRq userRegistrationRq) {
        //final UserRs userRs = userService.postUser(userRegistrationRq);
        return userService.postUser(userRegistrationRq);
    }

    @GetMapping("api/v1/users")
    public UserRs getUser(@RequestParam(required = false) final Long id,
                          @RequestParam(required = false) final String email) {
        return userService.getUser(id, email);
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

