package com.ManagementSystem.OnlineCourseManagementSystem.dto.enrollment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequestDTO {

    @NotNull
    private Long courseId;

    @NotNull
    private Long studentId;
}