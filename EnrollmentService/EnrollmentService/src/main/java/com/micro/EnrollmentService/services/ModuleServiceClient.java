package com.micro.EnrollmentService.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MODULESERVICE")
public interface ModuleServiceClient {
    @GetMapping("/modules//ModuleExists/{moduleCode}")
    ResponseEntity<Void> checkModuleExistence(@PathVariable String moduleCode);
}
