package com.ManagementSystem.OnlineCourseManagementSystem.repository;

import com.ManagementSystem.OnlineCourseManagementSystem.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Get all enrollments for a student
    List<Enrollment> findByStudentId(Long studentId);

    // Get all enrollments for a course
    List<Enrollment> findByCourseId(Long courseId);

    // Optional: find enrollment by student and course
    Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId);
}
