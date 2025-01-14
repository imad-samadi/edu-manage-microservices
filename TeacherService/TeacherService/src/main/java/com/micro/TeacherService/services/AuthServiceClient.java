package com.micro.TeacherService.services;



import com.micro.TeacherService.DTO.TeacherAuthDTO;
import com.micro.TeacherService.DTO.responseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "authServer") // Name of the Auth Service in Eureka
public interface AuthServiceClient {


    @PostMapping("/api/user/add")
    ResponseEntity<responseDTO> registerUser(@RequestBody TeacherAuthDTO authDTO);

   /* @PutMapping("/api/user/update/{matricule}")
    ResponseEntity<Void> updateUser(@PathVariable String matricule, @RequestBody studentAuthDTO authDTO);*/

    @DeleteMapping("/api/user/delete/{matricule}")
    ResponseEntity<Void> deleteUser(@PathVariable String matricule);
}
