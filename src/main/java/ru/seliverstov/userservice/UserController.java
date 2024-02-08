package ru.seliverstov.userservice;

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
import ru.seliverstov.userservice.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final List<User> users = new ArrayList<>();

    @PostMapping("api/v1/users")
    public UserRs postUser(@RequestBody UserRegistrationRq userRegistrationRq) {
        User user = new User(userRegistrationRq.getFullName(), userRegistrationRq.getRole());
        users.add(user);
        UserRs userRs = new UserRs(user.getId(), user.getFio(), user.getRole());
        return userRs;
    }

    @GetMapping("api/v1/users")
    public UserRs getUser(@RequestParam Long id) {
        User gettingUser = users.stream().filter(u -> u.getId().equals(id)).findFirst().orElseThrow();
        UserRs userRs = new UserRs(gettingUser.getId(), gettingUser.getFio(), gettingUser.getRole());
        return userRs;

    }

    @DeleteMapping("api/v1/users")
    public void deleteUser(@RequestParam Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    @PutMapping("api/v1/users")
    public UserRs updateUser(@RequestBody UserUpdateRq userUpdateRq) {
        User foundUser = users.stream().filter(u -> u.getFio().equals(userUpdateRq.getFio())).findFirst().orElseThrow();
        foundUser.setFio(userUpdateRq.getFio());
        UserRs userRs = new UserRs(foundUser.getId(), foundUser.getFio(), foundUser.getRole());
        return userRs;
    }
}
