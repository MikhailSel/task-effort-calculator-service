package ru.seliverstov.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Embeddable
public class TaskUserKey implements Serializable {
    @Column(name = "task_id", nullable = false)
    private Long taskId;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((taskId == null||userId == null) ? 0 : Objects.hash(taskId, userId));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TaskUserKey that = (TaskUserKey) obj;
        if (taskId == null||userId == null) {
            if (((TaskUserKey) obj).getTaskId() != null&&((TaskUserKey) obj).getUserId() != null)
                return false;
        } else if (!Objects.equals(taskId, that.taskId) && Objects.equals(userId, that.userId))
            return false;
        return true;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskUserKey that = (TaskUserKey) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, userId);
    }*/
}
}
