package com.micro.studentService.services;


import com.micro.studentService.DTO.responseDTO;
import com.micro.studentService.DTO.studentAuthDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "authServer") // Name of the Auth Service in Eureka
public interface AuthServiceClient {


    @PostMapping("/api/user/add")
    ResponseEntity<responseDTO> registerUser(@RequestBody studentAuthDTO authDTO);

    @PutMapping("/api/user/update/{matricule}")
    ResponseEntity<Void> updateUser(@PathVariable String matricule, @RequestBody studentAuthDTO authDTO);

    @DeleteMapping("/api/user/delete/{matricule}")
    ResponseEntity<Void> deleteUser(@PathVariable String matricule);
}
