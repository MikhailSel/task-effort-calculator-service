package ru.seliverstov.userservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Embeddable
public class TaskUserEstimationId implements Serializable {
    @Serial
    private static final long serialVersionUID = -2235367396042971937L;
    @Column(name = "task_id", nullable = false)
    private Long taskId;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskUserEstimationId that)) {
            return false;
        }

        return Objects.equals(taskId, that.taskId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(taskId);
        result = 31 * result + Objects.hashCode(userId);
        return result;
    }
}
