package com.micro.studentService.services;

import com.micro.studentService.DTO.ModuleOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ENROLLMENTSERVICE")
public interface EnrollmentServiceClient {

    @GetMapping("/enrollments/student/{studentId}/modules")
    ResponseEntity<List<ModuleOutDTO>> getModulesForStudent(@PathVariable String studentId);

    @DeleteMapping("/enrollments/cancel/student/{studentId}")
    ResponseEntity<Void> cancelEnrollmentsForStudent(@PathVariable String studentId);
}
