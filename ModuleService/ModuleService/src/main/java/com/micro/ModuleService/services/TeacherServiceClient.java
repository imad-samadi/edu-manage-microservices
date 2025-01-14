package com.micro.ModuleService.services;

import com.micro.ModuleService.DTO.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TEACHERSERVICE")
public interface TeacherServiceClient {

    @GetMapping("/teachers/{TCode}") // Endpoint in the Teacher Service
    ResponseEntity<Teacher> getTeacherByTCode(@PathVariable String TCode) ;

}
