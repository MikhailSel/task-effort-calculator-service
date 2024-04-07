package ru.seliverstov.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor

@Entity
@Table(name = "task_estimation")
public class TaskUserEstimation {
    @EmbeddedId
    private TaskUserKey id;
    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id",
        foreignKey = @ForeignKey(
            name = "fk_estimation_on_task"
        )
    )
    private Task task;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id",
        foreignKey = @ForeignKey(
            name = "fk_estimation_on_user"
        )
    )
    private User user;
    @Column(name = "days_per_person")
    private Long daysPerPerson;

    public TaskUserEstimation(Task task, User user, Long daysPerPerson) {
        this.task = task;
        this.user = user;
        this.daysPerPerson = daysPerPerson;
    }
}
