package ru.seliverstov.userservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.seliverstov.userservice.exception.ErrorCode;
import ru.seliverstov.userservice.exception.ServiceException;
import ru.seliverstov.userservice.mapper.UserMapper;
import ru.seliverstov.userservice.model.dto.UserRegistrationRq;
import ru.seliverstov.userservice.model.dto.UserRs;
import ru.seliverstov.userservice.model.dto.UserUpdateRq;
import ru.seliverstov.userservice.model.entity.User;
import ru.seliverstov.userservice.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserRs> findAll() {
        return userRepository.findAll()
            .stream()
            .map(userMapper::toUserRs)
            .toList();
    }

    /*public final UserRs postUser(final UserRegistrationRq userRegistrationRq) {
        final User user = new User(userRegistrationRq.getFullName(), userRegistrationRq.getRole());
        users.add(user);
        final UserRs userRs = new UserRs(user.getId(), user.getFio(), user.getRole());
        return userRs;
    }
*/
    public UserRs postUser(final UserRegistrationRq request) {
        final User user = User.builder()
            .fio(request.getFullName())
            //            .role(request.getRole())
            .build();
        userRepository.save(user);
        return userMapper.toUserRs(user);
    }
    //    public UserRs getUser(final Long id) {
    //        for (User user : users) {
    //            if (user.getId().equals(id)) {
    //                final UserRs userRs = new UserRs(user.getId(), user.getFio(), user.getRole());
    //                return userRs;
    //            }
    //        }
    //        return null;
    //    }

    public UserRs getUser(final Long id) {
        return userRepository.findById(id)
            .map(userMapper::toUserRs)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_001, id));
    }

    //    public void deleteUser(final Long id) {
    //        users.removeIf(user -> user.getId().equals(id));
    //    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    //    public UserRs updateUser(final UserUpdateRq userUpdateRq) {
    //        for (User user : users) {
    //            if (user.getId().equals(userUpdateRq.getId())) {
    //                user.setFio(userUpdateRq.getFio());
    //                final UserRs userRs = new UserRs(user.getId(), user.getFio(), user.getRole());
    //                return userRs;
    //            }
    //        }
    //        return null;
    //    }
    @Transactional
    public UserRs updateUser(final UserUpdateRq request) {
        final User user = userRepository.findById(request.getId())
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_001, request.getId()));
        user.setFio(request.getFio());
        return userMapper.toUserRs(user);
    }
}
