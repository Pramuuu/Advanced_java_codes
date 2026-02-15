package com.cap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Cacheable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "student")

// ✅ JPA Level Cache Enable
@Cacheable

// ✅ Hibernate Second Level Cache Strategy
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Student {

    @Id
    private int id;
    private String name;
    private String course;

    public Student() {}

    public Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    // ⭐ Optional but VERY IMPORTANT for good debugging/logging
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', course='" + course + "'}";
    }
}