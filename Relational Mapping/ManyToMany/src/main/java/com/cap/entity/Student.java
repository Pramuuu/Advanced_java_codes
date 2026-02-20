package com.cap.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
        name="student_course",
        joinColumns=@JoinColumn(name="student_id"),
        inverseJoinColumns=@JoinColumn(name="course_id")
    )
    private List<Course> courses;

    public Student() {}

    public Student(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<Course> getCourses() { return courses; }

    public void setName(String name) { this.name = name; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}