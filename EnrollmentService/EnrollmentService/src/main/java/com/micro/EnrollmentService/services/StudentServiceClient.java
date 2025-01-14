package com.micro.EnrollmentService.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "STUDENTSERVICE")
public interface StudentServiceClient {
    @GetMapping("/students//studentExists/{studentId}")

    ResponseEntity<Void> getStudentById(@PathVariable String studentId);
}
