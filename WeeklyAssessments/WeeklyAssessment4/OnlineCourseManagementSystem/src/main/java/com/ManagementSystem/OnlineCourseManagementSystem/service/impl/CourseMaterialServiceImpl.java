package com.ManagementSystem.OnlineCourseManagementSystem.service.impl;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.material.MaterialResponseDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.material.MaterialUploadDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.entity.Course;
import com.ManagementSystem.OnlineCourseManagementSystem.entity.CourseMaterial;
import com.ManagementSystem.OnlineCourseManagementSystem.exception.ResourceNotFoundException;
import com.ManagementSystem.OnlineCourseManagementSystem.repository.CourseMaterialRepository;
import com.ManagementSystem.OnlineCourseManagementSystem.repository.CourseRepository;
import com.ManagementSystem.OnlineCourseManagementSystem.service.CourseMaterialService;
import com.ManagementSystem.OnlineCourseManagementSystem.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseMaterialServiceImpl implements CourseMaterialService {

    private final CourseMaterialRepository materialRepository;
    private final CourseRepository courseRepository;

    @Override
    public MaterialResponseDTO uploadMaterial(MaterialUploadDTO dto) {

        // Fetch course
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // Save file to uploads/
        String fileName = FileUploadUtil.saveFile(dto.getFile());

        // Prepare entity
        CourseMaterial material = new CourseMaterial();
        material.setTitle(dto.getTitle());
        material.setFileName(fileName);
        material.setFileType(dto.getFile().getContentType());
        material.setFileUrl("/api/materials/" + fileName + "/download");
        material.setUploadDate(LocalDateTime.now()); // Use LocalDateTime
        material.setCourse(course);

        // Save to DB
        CourseMaterial saved = materialRepository.save(material);

        return mapToDTO(saved);
    }

    @Override
    public Resource downloadMaterial(Long materialId) {

        // Fetch material
        CourseMaterial material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found"));

        // Load file as Spring Resource
        return FileUploadUtil.loadFile(material.getFileName());
    }

    @Override
    public List<MaterialResponseDTO> getMaterialsByCourseId(Long courseId) {

        return materialRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private MaterialResponseDTO mapToDTO(CourseMaterial m) {

        MaterialResponseDTO dto = new MaterialResponseDTO();

        dto.setId(m.getId());
        dto.setTitle(m.getTitle());
        dto.setFileName(m.getFileName());
        dto.setFileType(m.getFileType());
        dto.setFileUrl(m.getFileUrl());
        dto.setUploadDate(m.getUploadDate()); // LocalDateTime

        return dto;
    }
}