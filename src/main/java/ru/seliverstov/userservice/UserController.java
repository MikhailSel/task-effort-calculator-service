package ru.seliverstov.userservice;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final List<User> users = new ArrayList<>();

    @GetMapping("api/v1/users")
    public List<User> getUsers() {
        return users;
    }

    @PostMapping("api/v1/users")
    public User postUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    @DeleteMapping("api/v1/users")
    public void deleteUser(@RequestParam Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    @PutMapping("api/v1/users")
    public User updateUser(@RequestBody User request) {
        User foundUser = users.stream()
            .filter(u -> u.getId().equals(request.getId()))
            .findFirst()
            .orElseThrow();

        foundUser.setEmail(request.getEmail());
        return foundUser;
    }
}
