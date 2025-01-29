package com.micro.EnrollmentService.services;

import com.micro.EnrollmentService.DTO.ModuleOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "MODULESERVICE")
public interface ModuleServiceClient {
    @GetMapping("/modules/ModuleExists/{moduleCode}")
    ResponseEntity<Void> checkModuleExistence(@PathVariable String moduleCode);

    @PostMapping("/modules/getModulesByCodes")
    ResponseEntity<List<ModuleOutDTO>> getModulesByCodes(@RequestBody List<String> moduleCodes);
}
