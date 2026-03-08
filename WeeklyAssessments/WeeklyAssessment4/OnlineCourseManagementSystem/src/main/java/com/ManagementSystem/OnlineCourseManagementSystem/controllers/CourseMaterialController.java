package com.ManagementSystem.OnlineCourseManagementSystem.controllers;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.material.MaterialResponseDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.material.MaterialUploadDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.service.CourseMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class CourseMaterialController {

    private final CourseMaterialService materialService;

    // Upload Material
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MaterialResponseDTO uploadMaterial(
            @Valid @ModelAttribute MaterialUploadDTO dto) {

        return materialService.uploadMaterial(dto);
    }

    // Download Material
    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadMaterial(@PathVariable Long id) {

        Resource resource = materialService.downloadMaterial(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // List Materials by Course
    @GetMapping("/course/{courseId}")
    public List<MaterialResponseDTO> getMaterialsByCourse(@PathVariable Long courseId) {

        return materialService.getMaterialsByCourseId(courseId);
    }
}