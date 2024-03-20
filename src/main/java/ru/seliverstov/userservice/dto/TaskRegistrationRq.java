package ru.seliverstov.userservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskRegistrationRq {
    private Long taskId;
    private String taskName;
}

