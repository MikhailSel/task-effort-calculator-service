package ru.seliverstov.userservice.support;

import lombok.experimental.UtilityClass;
import ru.seliverstov.userservice.model.entity.Task;
import ru.seliverstov.userservice.model.entity.User;

@UtilityClass
public class DataProvider {
    public static Task.TaskBuilder<?, ?> prepareTask() {
        return Task.builder()
            .name("task1");
    }

    public static User.UserBuilder<?, ?> prepareUser() {
        return User.builder()
            .fio("user1");
    }
}
