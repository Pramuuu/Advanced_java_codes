package com.ManagementSystem.OnlineCourseManagementSystem.controllers;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.enrollment.EnrollmentRequestDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.enrollment.EnrollmentResponseDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public EnrollmentResponseDTO enrollStudent(
            @Valid @RequestBody EnrollmentRequestDTO request) {

        return enrollmentService.enrollStudent(request);
    }

    @GetMapping("/student/{studentId}")
    public List<EnrollmentResponseDTO> getStudentEnrollments(
            @PathVariable Long studentId) {

        return enrollmentService.getEnrollmentsByStudent(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<EnrollmentResponseDTO> getCourseEnrollments(
            @PathVariable Long courseId) {

        return enrollmentService.getEnrollmentsByCourse(courseId);
    }
}
