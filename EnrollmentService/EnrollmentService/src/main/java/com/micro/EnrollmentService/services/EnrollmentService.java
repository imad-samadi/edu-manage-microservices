package com.micro.EnrollmentService.services;

import com.micro.EnrollmentService.DTO.EnrollmentDTO;
import com.micro.EnrollmentService.enteties.Enrollment;
import com.micro.EnrollmentService.exceptions.notFoundException;
import com.micro.EnrollmentService.repo.EnrollmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnrollmentService {

    private EnrollmentRepository enrollmentRepository;
    private StudentServiceClient studentServiceClient;
    private ModuleServiceClient moduleServiceClient;

    public Enrollment addEnrollment(EnrollmentDTO enrollmentDTO) {

        ResponseEntity<Void> studentResponse = studentServiceClient.getStudentById(enrollmentDTO.getStudentMatr());
        if (!studentResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Student not found.");
        }


        ResponseEntity<Void> moduleResponse = moduleServiceClient.checkModuleExistence(enrollmentDTO.getModuleCode());
        if (!moduleResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Module not found.");
        }


        Enrollment enrollment = Enrollment.builder()
                .studentId(enrollmentDTO.getStudentMatr())
                .moduleCode(enrollmentDTO.getModuleCode())
                .enrollmentDate(LocalDateTime.now())
                .build();

        return enrollmentRepository.save(enrollment);
    }

    public List<String> getModuleCodesByStudent(String studentId) {
        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(Enrollment::getModuleCode) // Extract module codes
                .collect(Collectors.toList());
    }

    public List<String> getStudentIdsByModule(String moduleCode) {
        return enrollmentRepository.findByModuleCode(moduleCode).stream()
                .map(Enrollment::getStudentId)
                .collect(Collectors.toList());
    }

    public List<EnrollmentDTO> getAllEnrollments() {

        List<Enrollment> enrollments = this.enrollmentRepository.findAll();


        return enrollments.stream()
                .map(enrollment -> EnrollmentDTO.builder()
                        .studentMatr(enrollment.getStudentId()) // Assuming you have getStudentId() in Enrollment entity
                        .moduleCode(enrollment.getModuleCode()) // Assuming you have getModuleCode() in Enrollment entity
                        .enrollmentDate(enrollment.getEnrollmentDate()) // Assuming you have getEnrollmentDate() in Enrollment entity
                        .build())
                .collect(Collectors.toList());
    }
    public void CancelEnrollment(String studentId,String moduleCode) {

        Enrollment enrollment = this.enrollmentRepository.findByStudentIdAndModuleCode(studentId, moduleCode)
                .orElseThrow(() -> new notFoundException("Enrollment not found for studentId: " + studentId + " and moduleCode: " + moduleCode));


        enrollmentRepository.delete(enrollment);
    }

    public void cancelEnrollmentsForStudent(String studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        // If student has enrollments, delete them
        if (!enrollments.isEmpty()) {
            enrollmentRepository.deleteAll(enrollments);
        }
    }

}



