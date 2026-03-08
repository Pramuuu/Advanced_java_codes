package com.ManagementSystem.OnlineCourseManagementSystem.service.impl;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.course.CourseRequestDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.course.CourseResponseDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.entity.Course;
import com.ManagementSystem.OnlineCourseManagementSystem.entity.User;
import com.ManagementSystem.OnlineCourseManagementSystem.exception.ResourceNotFoundException;
import com.ManagementSystem.OnlineCourseManagementSystem.repository.CourseRepository;
import com.ManagementSystem.OnlineCourseManagementSystem.repository.UserRepository;
import com.ManagementSystem.OnlineCourseManagementSystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    // ===============================
    // CREATE COURSE
    // ===============================

    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO createCourse(CourseRequestDTO request, Long instructorId) {

        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Instructor not found"));

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setDuration(request.getDuration());
        course.setLevel(request.getLevel());
        course.setInstructor(instructor);

        // Timestamp handling
        course.setCreatedAt(LocalDateTime.now());

        return mapToDTO(courseRepository.save(course));
    }

    // ===============================
    // UPDATE COURSE
    // ===============================

    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO request) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setDuration(request.getDuration());
        course.setLevel(request.getLevel());

        course.setUpdatedAt(LocalDateTime.now());

        return mapToDTO(courseRepository.save(course));
    }

    // ===============================
    // DELETE COURSE
    // ===============================

    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    // ===============================
    // GET COURSE BY ID
    // ===============================

    @Override
    @Cacheable(value = "course", key = "#id")
    public CourseResponseDTO getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));

        return mapToDTO(course);
    }

    // ===============================
    // GET ALL COURSES (PAGINATION + CACHE)
    // ===============================

    @Override
    @Cacheable(value = "courses")
    public Page<CourseResponseDTO> getAllCourses(Pageable pageable) {

        return courseRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    // ===============================
    // MAPPER METHOD
    // ===============================

    private CourseResponseDTO mapToDTO(Course course) {

        CourseResponseDTO dto = new CourseResponseDTO();

        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        dto.setDuration(course.getDuration());
        dto.setLevel(course.getLevel());

        if (course.getInstructor() != null) {
            dto.setInstructorName(course.getInstructor().getFullName());
        }

        dto.setCreatedAt(course.getCreatedAt());

        return dto;
    }
}