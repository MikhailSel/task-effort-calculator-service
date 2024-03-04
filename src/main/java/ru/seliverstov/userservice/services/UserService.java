package ru.seliverstov.userservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.seliverstov.userservice.dto.UserRegistrationRq;
import ru.seliverstov.userservice.dto.UserRs;
import ru.seliverstov.userservice.dto.UserUpdateRq;
import ru.seliverstov.userservice.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public UserRs postUser(UserRegistrationRq userRegistrationRq) {
        User user = new User(userRegistrationRq.getFullName(), userRegistrationRq.getRole());
        users.add(user);
        UserRs userRs = new UserRs(user.getId(), user.getFio(), user.getRole());
        return userRs;
    }

    public UserRs getUser(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                UserRs userRs = new UserRs(user.getId(), user.getFio(), user.getRole());
                return userRs;
            }
        }
        return null;
    }

    public void deleteUser(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public UserRs updateUser(UserUpdateRq userUpdateRq) {
        for (User user : users) {
            if (user.getId().equals(userUpdateRq.getId())) {
                user.setFio(userUpdateRq.getFio());
                UserRs userRs = new UserRs(user.getId(), user.getFio(), user.getRole());
                return userRs;
            }
        }
        return null;
    }
}
