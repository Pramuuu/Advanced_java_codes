package com.ManagementSystem.OnlineCourseManagementSystem.repository;

import com.ManagementSystem.OnlineCourseManagementSystem.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
    List<CourseMaterial> findByCourseId(Long courseId);
}