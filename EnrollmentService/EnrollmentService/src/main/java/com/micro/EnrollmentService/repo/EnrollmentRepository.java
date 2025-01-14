package com.micro.EnrollmentService.repo;

import com.micro.EnrollmentService.enteties.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {



    List<Enrollment> findByStudentId(String studentId);
    List<Enrollment> findByModuleCode(String moduleCode);
    Optional<Enrollment> findByStudentIdAndModuleCode(String studentId, String moduleCode);
}
