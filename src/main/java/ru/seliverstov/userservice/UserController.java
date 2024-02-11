package ru.seliverstov.userservice;

import org.springframework.web.bind.annotation.*;
import ru.seliverstov.userservice.dto.UserRegistrationRq;
import ru.seliverstov.userservice.dto.UserRs;
import ru.seliverstov.userservice.dto.UserUpdateRq;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/v1/users")
    public UserRs postUser(@RequestBody UserRegistrationRq userRegistrationRq) {
        UserRs userRs = userService.postUser(userRegistrationRq);
        return userRs;
    }

    @GetMapping("api/v1/users")
    public UserRs getUser(@RequestParam Long id) {
        UserRs userRs = userService.getUser(id);
        return userRs;
    }

    @DeleteMapping("api/v1/users")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("api/v1/users")
    public UserRs updateUser(@RequestBody UserUpdateRq userUpdateRq) {
        UserRs userRs = userService.updateUser(userUpdateRq);
        return userRs;
    }
}

