package ru.seliverstov.taskservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEstimationRs {
    private Long taskId;
    private Long userId;
    private Long daysPerTask;
}
