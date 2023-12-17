package ru.seliverstov.userservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @GetMapping ("api/v1/users")
    public List <User> getUsers() {
        return List.of(
                new User("mail@mail.ru"),
                new User("mail1@mail1.ru")
        );
    }
}
