package ru.seliverstov.userservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.seliverstov.userservice.exception.ErrorCode;
import ru.seliverstov.userservice.exception.ServiceException;
import ru.seliverstov.userservice.mapper.UserMapper;
import ru.seliverstov.userservice.model.dto.UserRegistrationRq;
import ru.seliverstov.userservice.model.dto.UserRs;
import ru.seliverstov.userservice.model.dto.UserUpdateRq;
import ru.seliverstov.userservice.model.entity.Role;
import ru.seliverstov.userservice.model.entity.User;
import ru.seliverstov.userservice.repository.RoleRepository;
import ru.seliverstov.userservice.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public List<UserRs> findAll() {
        return userRepository.findAll()
            .stream()
            .map(userMapper::toUserRs)
            .toList();
    }

    public UserRs postUser(final UserRegistrationRq request) {
        final Role role = roleRepository.findByRoleName(request.getRole());
        final User user = User.builder()
            .fio(request.getFullName())
            .role(role)
            .build();
        userRepository.save(user);
        return userMapper.toUserRs(user);
    }

    public UserRs getUser(final Long id, final String email) {
        if (id != null) {
            return userRepository.findById(id)
                .map(userMapper::toUserRs)
                .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_002, id));
        } else if (StringUtils.hasText(email)) {
            return userRepository.findByEmail(email)
                .map(userMapper::toUserRs)
                .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_006, email));
        }

        throw new ServiceException(ErrorCode.ERR_CODE_005);
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserRs updateUser(final UserUpdateRq request) {
        final User user = userRepository.findById(request.getId())
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_001, request.getId()));
        user.setFio(request.getFio());
        return userMapper.toUserRs(user);
    }
}
