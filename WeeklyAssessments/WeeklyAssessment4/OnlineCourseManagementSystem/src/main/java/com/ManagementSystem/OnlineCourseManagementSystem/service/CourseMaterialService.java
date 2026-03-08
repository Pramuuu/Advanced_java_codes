package com.ManagementSystem.OnlineCourseManagementSystem.service;


import com.ManagementSystem.OnlineCourseManagementSystem.dto.material.MaterialResponseDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.material.MaterialUploadDTO;
import org.springframework.core.io.Resource;

import java.util.List;

public interface CourseMaterialService {

    MaterialResponseDTO uploadMaterial(MaterialUploadDTO dto);

    Resource downloadMaterial(Long materialId);

    List<MaterialResponseDTO> getMaterialsByCourseId(Long courseId);
}