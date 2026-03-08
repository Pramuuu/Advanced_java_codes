package com.ManagementSystem.OnlineCourseManagementSystem.service;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.enrollment.EnrollmentRequestDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.enrollment.EnrollmentResponseDTO;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO request);

    List<EnrollmentResponseDTO> getEnrollmentsByStudent(Long studentId);

    List<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId);
}
