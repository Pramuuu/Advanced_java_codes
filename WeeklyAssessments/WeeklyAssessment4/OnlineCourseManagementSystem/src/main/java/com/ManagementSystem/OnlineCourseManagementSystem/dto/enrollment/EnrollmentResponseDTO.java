package com.ManagementSystem.OnlineCourseManagementSystem.dto.enrollment;

import com.ManagementSystem.OnlineCourseManagementSystem.entity.EnrollmentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EnrollmentResponseDTO {

    private Long id;
    private String courseTitle;
    private String studentName;

    private EnrollmentStatus status;
    private Double progressPercentage;

    private LocalDate enrollmentDate;
}
