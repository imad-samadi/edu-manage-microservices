package com.micro.ModuleService.controllers;

import com.micro.ModuleService.DTO.ModuleDTO;
import com.micro.ModuleService.DTO.ModuleOutDTO;
import com.micro.ModuleService.exceptions.notFoundException;
import com.micro.ModuleService.services.impl.ModuleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.micro.ModuleService.entities.Module;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/modules")
@AllArgsConstructor
public class ModuleController {

    private ModuleServiceImpl moduleService;


    @Operation(
            summary = "ADD Module",
            description = "CREATING NEW Module"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "IF THE MODULE CODE ALREDY EXISTS"
            )
    })
    @PostMapping("/add")
    public ResponseEntity<Module> addModule(@Valid @RequestBody ModuleDTO moduleInDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.moduleService.addModule(moduleInDTO));
    }

    @Operation(
            summary = "GET Module",
            description = "GET Module by Code"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "IF THE MODULE WITH THE CODE GIVEN DONT EXIST"
            )
    })
    @GetMapping("/{code}")
    public ResponseEntity<ModuleOutDTO> getModuleByCode(@PathVariable String code) throws notFoundException {
        ModuleOutDTO module = moduleService.getModuleByCode(code);
        return ResponseEntity.ok(module);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "GET ALL MODULES + PROF INFO"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @GetMapping("/all")
    public ResponseEntity<List<ModuleOutDTO>> getAllModules() {
        List<ModuleOutDTO> modules = moduleService.getAllModules();
        return ResponseEntity.ok(modules);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "UPDATE THE MODULE (NOT THE CODE)"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @PutMapping("/{code}")
    public ResponseEntity<Void> updateModule(
            @PathVariable String code,
            @Valid @RequestBody ModuleDTO moduleInDTO
    )  {
        moduleService.updateModule(code, moduleInDTO);
        return ResponseEntity.ok().build();
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "DELETE THE MODULE BY THE CODE"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "THE CODE GIVEN HAS NO MODULE"
            )
    })
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteModule(@PathVariable String code)  {
        moduleService.deleteModule(code);
        return ResponseEntity.noContent().build();
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Get the modules by the prof code "
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "EMPTY LIST IF THE TEACHER CODE INCORRECT OR DONT HAVE ANY MODULES YET"
            )
    })
    @GetMapping("/Tcode/{teacherCode}")
    public ResponseEntity<List<ModuleDTO> > getModuleByTeacherCode(@PathVariable String teacherCode) {
        return ResponseEntity.ok(this.moduleService.getModulesByTeacherTCode(teacherCode));
    }

    @GetMapping("/ModuleExists/{code}")
    public ResponseEntity<Boolean> moduleExists(@PathVariable String code) {
        this.moduleService.checkModuleExists(code);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/cancelAssign/{TCODE}")
    public ResponseEntity<Void> cancelAssign(@PathVariable String TCODE) {
        moduleService.cancelAssign(TCODE);
        return ResponseEntity.noContent().build();
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Get the modules info by the liste of modules Codes"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "if Any code in the list HAS NO MODULE"
            )
    })
    @PostMapping("/getModulesByCodes")
    public ResponseEntity<List<ModuleOutDTO>> getModulesByCodes(@RequestBody List<String> moduleCodes) {
        List<ModuleOutDTO> modules = moduleService.getModulesByCodes(moduleCodes);
        return ResponseEntity.ok(modules);
    }



}
