package com.ManagementSystem.OnlineCourseManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate enrollmentDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    private double progressPercentage;

    // Student reference
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    // Course reference
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
