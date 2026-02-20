package com.cap.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;

    @ManyToMany(mappedBy="courses")
    private List<Student> students;

    public Course() {}

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
