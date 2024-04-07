package ru.seliverstov.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "students")
public class Student {

    @Id
    public Long id;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "course_like",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )*/
    @Builder.Default
    @ToString.Exclude
    //List<Course> likedCourses = new ArrayList<>();
    @OneToMany(mappedBy = "student")
    List<CourseRating> ratings = new ArrayList<>();
    /*public void toCourse(Course course) {
        this.likedCourses.add(course);
        course.likes.add(this);
    }*/

}
