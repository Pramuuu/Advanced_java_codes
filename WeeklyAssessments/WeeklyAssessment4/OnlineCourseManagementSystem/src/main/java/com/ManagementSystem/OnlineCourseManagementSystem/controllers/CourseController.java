package com.ManagementSystem.OnlineCourseManagementSystem.controllers;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.course.CourseRequestDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.course.CourseResponseDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // Create Course
    @PostMapping
    public CourseResponseDTO createCourse(
            @RequestParam Long instructorId,
            @Valid @RequestBody CourseRequestDTO request) {

        return courseService.createCourse(request, instructorId);
    }

    // Update Course
    @PutMapping("/{id}")
    public CourseResponseDTO updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDTO request) {

        return courseService.updateCourse(id, request);
    }

    // Delete Course
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);
        return "Course deleted successfully";
    }

    // Pagination + Sorting
    @GetMapping
    public Page<CourseResponseDTO> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title,asc") String[] sort
    ) {

        Sort.Direction direction =
                sort[1].equalsIgnoreCase("desc") ?
                        Sort.Direction.DESC :
                        Sort.Direction.ASC;

        Sort sortOrder = Sort.by(direction, sort[0]);

        Pageable pageable = PageRequest.of(page, size, sortOrder);

        return courseService.getAllCourses(pageable);
    }

    // Get Course By ID
    @GetMapping("/{id}")
    public CourseResponseDTO getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
}