package com.ManagementSystem.OnlineCourseManagementSystem.dto.material;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MaterialUploadDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotNull(message = "File is required")
    private MultipartFile file;
}