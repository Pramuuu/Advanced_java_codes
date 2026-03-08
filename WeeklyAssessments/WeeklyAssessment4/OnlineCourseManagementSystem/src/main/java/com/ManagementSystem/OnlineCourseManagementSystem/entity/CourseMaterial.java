package com.ManagementSystem.OnlineCourseManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_materials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String fileName;

    private String fileType;

    private String fileUrl;

    private LocalDateTime uploadDate;

    // Material belongs to a course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
