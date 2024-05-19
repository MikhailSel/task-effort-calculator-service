package ru.seliverstov.userservice.mapper;

import org.springframework.stereotype.Component;
import ru.seliverstov.userservice.model.dto.UserRs;
import ru.seliverstov.userservice.model.entity.User;

@Component
public class UserMapper {
    public UserRs toUserRs(final User user) {
        return UserRs.builder()
            .id(user.getId())
            .fio(user.getFio())
            .role(user.getRole().getName())
            .build();
    }
}
