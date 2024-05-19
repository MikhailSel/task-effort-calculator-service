package ru.seliverstov.userservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "fio", unique = true, nullable = false, length = 100)
    private String fio;

    @OneToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private Role role;

    //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    //    @Builder.Default
    //    @ToString.Exclude
    //    private List<TaskUserEstimation> taskUserEstimations = new ArrayList<>();
}
