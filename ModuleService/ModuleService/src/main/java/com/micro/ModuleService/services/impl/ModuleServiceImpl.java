package com.micro.ModuleService.services.impl;

import com.micro.ModuleService.DTO.ModuleDTO;
import com.micro.ModuleService.DTO.ModuleOutDTO;
import com.micro.ModuleService.DTO.Teacher;
import com.micro.ModuleService.exceptions.ModuleExists;
import com.micro.ModuleService.exceptions.notFoundException;
import com.micro.ModuleService.repo.ModuleRepository;
import com.micro.ModuleService.services.TeacherServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.micro.ModuleService.entities.Module;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModuleServiceImpl  {

    private ModuleRepository moduleRepository;
    private TeacherServiceClient teacherServiceClient;



    public Module addModule(ModuleDTO moduleInDTO) {

        if (moduleRepository.findByCode(moduleInDTO.getCode()).isPresent()) {
            throw new ModuleExists("A module with the code " + moduleInDTO.getCode() + " already exists.");
        }
        if(moduleInDTO.getTeacherId()!=null){
            this.getTeacherByTCode(moduleInDTO.getTeacherId());
        }



        Module m = Module.builder()
                .code(moduleInDTO.getCode())
                .name(moduleInDTO.getName())
                .description(moduleInDTO.getDescription())
                .teacherId(moduleInDTO.getTeacherId())
                .createdAt(LocalDateTime.now())
                .build() ;


        return this.moduleRepository.save(m);
    }

public ModuleOutDTO getModuleByCode(String Code) {
    Module module = moduleRepository.findByCode(Code)
            .orElseThrow(() -> new notFoundException("Module not found with ID: " + Code));
    Teacher t = null  ;
    if(module.getTeacherId()!=null  && !module.getTeacherId().isEmpty()){
        t = this.getTeacherByTCode(module.getTeacherId());
    }

    return ModuleOutDTO.builder()
            .code(module.getCode())
            .name(module.getName())
            .description(module.getDescription())
            .teacher(t)
            .build();

}
    public Teacher getTeacherByTCode(String TCode)  {

        ResponseEntity<Teacher> response = teacherServiceClient.getTeacherByTCode(TCode);


        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new notFoundException("Teacher not found with TCode: " + TCode);
        }
    }

    public List<ModuleOutDTO> getAllModules() {

        List<Module> modules = moduleRepository.findAll();


        return modules.stream().map(module -> {
            Teacher teacher  = null ;
            if(module.getTeacherId()!=null){
                teacher = this.getTeacherByTCode(module.getTeacherId());
            }

            return ModuleOutDTO.builder()
                    .code(module.getCode())
                    .name(module.getName())
                    .description(module.getDescription())
                    .teacher(teacher)
                    .build();
        }).collect(Collectors.toList());
    }







    public void updateModule(String code, ModuleDTO moduleInDTO) {
        Module module = moduleRepository.findByCode(code)
                .orElseThrow(() -> new notFoundException("Module not found with ID: " + code));


        module.setName(moduleInDTO.getName());
        module.setDescription(moduleInDTO.getDescription());
        module.setTeacherId(moduleInDTO.getTeacherId());
        module.setUpdatedAt(LocalDateTime.now());


        moduleRepository.save(module);


    }


    public void deleteModule(String Code) throws notFoundException {

        Module module = moduleRepository.findByCode(Code)
                .orElseThrow(() -> new notFoundException("Module not found with ID: " + Code));

        moduleRepository.delete(module);
    }

    public List<ModuleDTO> getModulesByTeacherTCode(String TCode) throws notFoundException {



        List<Module> modules = moduleRepository.findByTeacherId(TCode)
                .orElseGet(ArrayList::new);


        return modules.stream()
                .map(module -> ModuleDTO.builder()
                        .code(module.getCode())
                        .name(module.getName())
                        .description(module.getDescription())
                        .teacherId(module.getTeacherId())
                        .build())
                .collect(Collectors.toList());
    }

    public void checkModuleExists(String code)  {
        this.moduleRepository.findByCode(code).orElseThrow(() -> new notFoundException("Module not found with ID: " + code));
    }

    public void cancelAssign(String TCODE) {
        // Find all modules assigned to the teacher
        Optional<List<Module>> optionalModules = moduleRepository.findByTeacherId(TCODE);

        // If modules are found, unassign the teacher from them
        if (optionalModules.isPresent()) {
            List<Module> modules = optionalModules.get();
            if (!modules.isEmpty()) {
                for (Module module : modules) {
                    module.setTeacherId(null); // Unassign the teacher
                    moduleRepository.save(module); // Save the updated module
                }
            }
        }
    }

    public List<ModuleOutDTO> getModulesByCodes(List<String> moduleCodes) {
        List<ModuleOutDTO> moduleOutDTOs = new ArrayList<>();

        for (String code : moduleCodes) {
            Module module = moduleRepository.findByCode(code)
                    .orElseThrow(() -> new notFoundException("Module not found with code: " + code));

            Teacher teacher = null;
            if (module.getTeacherId() != null) {
                teacher = this.getTeacherByTCode(module.getTeacherId());
            }

            ModuleOutDTO moduleOutDTO = ModuleOutDTO.builder()
                    .code(module.getCode())
                    .name(module.getName())
                    .description(module.getDescription())
                    .teacher(teacher)
                    .build();

            moduleOutDTOs.add(moduleOutDTO);
        }

        return moduleOutDTOs;
    }






}
