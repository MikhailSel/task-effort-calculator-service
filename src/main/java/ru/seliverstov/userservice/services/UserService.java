package ru.seliverstov.userservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.seliverstov.userservice.dto.UserRegistrationRq;
import ru.seliverstov.userservice.dto.UserRs;
import ru.seliverstov.userservice.dto.UserUpdateRq;
import ru.seliverstov.userservice.model.User;
import ru.seliverstov.userservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    //private final List<User> users = new ArrayList<>();
private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }



 /*   public final UserRs postUser(final UserRegistrationRq userRegistrationRq) {
        final User user = new User(userRegistrationRq.getFullName(), userRegistrationRq.getRole());
        users.add(user);
        final UserRs userRs = new UserRs(user.getId(), user.getFio(), user.getRole());
        return userRs;
    }

    public UserRs getUser(final Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                final UserRs userRs = new UserRs(user.getId(), user.getFio(), user.getRole());
                return userRs;
            }
        }
        return null;
    }

    public void deleteUser(final Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public UserRs updateUser(final UserUpdateRq userUpdateRq) {
        for (User user : users) {
            if (user.getId().equals(userUpdateRq.getId())) {
                user.setFio(userUpdateRq.getFio());
                final UserRs userRs = new UserRs(user.getId(), user.getFio(), user.getRole());
                return userRs;
            }
        }
        return null;
    } */
}
