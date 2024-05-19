package ru.seliverstov.taskservice.support;

import lombok.experimental.UtilityClass;
import ru.seliverstov.taskservice.model.entity.Task;

@UtilityClass
public class DataProvider {
    public static Task.TaskBuilder<?, ?> prepareTask() {
        return Task.builder()
            .name("task1");
    }
}
