package com.ManagementSystem.OnlineCourseManagementSystem.repository;

import com.ManagementSystem.OnlineCourseManagementSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // Get courses by instructor id
    List<Course> findByInstructorId(Long instructorId);
}
