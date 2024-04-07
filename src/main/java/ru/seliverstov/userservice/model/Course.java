package ru.seliverstov.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "courses")
public class Course {

    @Id
    Long id;

    //@ManyToMany(mappedBy = "likedCourses", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "course")
    List<CourseRating> ratings = new ArrayList<>();
    //List<Student> likes = new ArrayList<>();
    /*public void toStudent(Student student) {
        this.likes.add(student);
        student.likedCourses.add(this);
    }*/
}
