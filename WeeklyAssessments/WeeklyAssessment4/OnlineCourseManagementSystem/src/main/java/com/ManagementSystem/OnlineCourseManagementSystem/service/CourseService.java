package com.ManagementSystem.OnlineCourseManagementSystem.service;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.course.CourseRequestDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.course.CourseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    CourseResponseDTO createCourse(CourseRequestDTO request, Long instructorId);

    CourseResponseDTO updateCourse(Long id, CourseRequestDTO request);

    void deleteCourse(Long id);

    CourseResponseDTO getCourseById(Long id);

    Page<CourseResponseDTO> getAllCourses(Pageable pageable);
}
