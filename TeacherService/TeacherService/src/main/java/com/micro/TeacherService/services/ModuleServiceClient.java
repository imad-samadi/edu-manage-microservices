package com.micro.TeacherService.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.micro.TeacherService.DTO.Module ;
import java.util.List;

@FeignClient(name = "MODULESERVICE")
public interface ModuleServiceClient {

    @GetMapping("/modules/Tcode/{Tcode}")
    List<Module> getModulesByTeacherId(@PathVariable String Tcode);
    @GetMapping("/modules/cancelAssign/{TCODE}")
    ResponseEntity<Void> cancelAssign(@PathVariable String TCODE);
}
