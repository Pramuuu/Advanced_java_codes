package com.ManagementSystem.OnlineCourseManagementSystem.service.impl;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.enrollment.EnrollmentRequestDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.enrollment.EnrollmentResponseDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.entity.Course;
import com.ManagementSystem.OnlineCourseManagementSystem.entity.Enrollment;
import com.ManagementSystem.OnlineCourseManagementSystem.entity.EnrollmentStatus;
import com.ManagementSystem.OnlineCourseManagementSystem.entity.User;
import com.ManagementSystem.OnlineCourseManagementSystem.exception.ResourceNotFoundException;
import com.ManagementSystem.OnlineCourseManagementSystem.repository.CourseRepository;
import com.ManagementSystem.OnlineCourseManagementSystem.repository.EnrollmentRepository;
import com.ManagementSystem.OnlineCourseManagementSystem.repository.UserRepository;
import com.ManagementSystem.OnlineCourseManagementSystem.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO request) {

        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        enrollment.setProgressPercentage(0.0);

        return mapToDTO(enrollmentRepository.save(enrollment));
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByStudent(Long studentId) {

        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId) {

        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private EnrollmentResponseDTO mapToDTO(Enrollment enrollment) {

        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
        dto.setId(enrollment.getId());
        dto.setCourseTitle(enrollment.getCourse().getTitle());
        dto.setStudentName(enrollment.getStudent().getFullName());
        dto.setStatus(enrollment.getStatus());
        dto.setProgressPercentage(enrollment.getProgressPercentage());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());

        return dto;
    }
}