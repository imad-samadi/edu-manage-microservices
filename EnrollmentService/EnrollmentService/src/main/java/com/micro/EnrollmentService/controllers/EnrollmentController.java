package com.micro.EnrollmentService.controllers;

import com.micro.EnrollmentService.DTO.EnrollmentDTO;
import com.micro.EnrollmentService.DTO.ModuleOutDTO;
import com.micro.EnrollmentService.enteties.Enrollment;
import com.micro.EnrollmentService.services.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@AllArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;


    @PostMapping
    public ResponseEntity<EnrollmentDTO> addEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = enrollmentService.addEnrollment(enrollmentDTO);
        EnrollmentDTO responseDTO = EnrollmentDTO.builder()
                .studentMatr(enrollment.getStudentId())
                .moduleCode(enrollment.getModuleCode())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Endpoint to get all enrollments
    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        List<EnrollmentDTO> enrollmentDTOs = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollmentDTOs);
    }


    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<String>> getModulesByStudent(@PathVariable String studentId) {
        List<String> moduleCodes = enrollmentService.getModuleCodesByStudent(studentId);
        return ResponseEntity.ok(moduleCodes);
    }


    @GetMapping("/module/{moduleCode}")
    public ResponseEntity<List<String>> getStudentsByModule(@PathVariable String moduleCode) {
        List<String> studentIds = enrollmentService.getStudentIdsByModule(moduleCode);
        return ResponseEntity.ok(studentIds);
    }


    @DeleteMapping("/cancel")
    public ResponseEntity<Void> cancelEnrollment(@RequestParam String studentId, @RequestParam String moduleCode) {
        enrollmentService.CancelEnrollment(studentId, moduleCode);
        return ResponseEntity.noContent().build(); // 204 No Content on successful deletion
    }


    @DeleteMapping("/cancel/student/{studentId}")
    public ResponseEntity<Void> cancelEnrollmentsForStudent(@PathVariable String studentId) {
        enrollmentService.cancelEnrollmentsForStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/student/{studentId}/modules")
    public ResponseEntity<List<ModuleOutDTO>> getModulesForStudent(@PathVariable String studentId) {
        List<ModuleOutDTO> modules = enrollmentService.getModulesForStudent(studentId);
        return ResponseEntity.ok(modules);
    }

}
