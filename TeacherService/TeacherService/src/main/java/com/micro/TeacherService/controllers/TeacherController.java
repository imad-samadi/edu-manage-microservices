package com.micro.TeacherService.controllers;

import com.micro.TeacherService.DTO.TeacherInDTO;
import com.micro.TeacherService.DTO.TeacherOutDTO;
import com.micro.TeacherService.entetites.Teacher;
import com.micro.TeacherService.exceptions.notFoundException;
import com.micro.TeacherService.services.ITeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@AllArgsConstructor
public class TeacherController {

    private ITeacherService teacherService;



    @Operation(
            summary = "CREATE Teacher",
            description = "CREATING NEW Student"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@Valid @RequestBody TeacherInDTO teacherInDTO) {
        Teacher teacher = teacherService.addTeacher(teacherInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }

    @Operation(
            summary = "GET Teacher",
            description = "Get Teacher By TCODE WITH MODULES"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "ALL THE TEACHERS WITH MODULES"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @GetMapping("/{TCode}")
    public ResponseEntity<TeacherOutDTO> getTeacherByTCode(@PathVariable String TCode) throws notFoundException {
        TeacherOutDTO teacher = teacherService.getTeacherWithModules(TCode);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }



    @GetMapping
    public ResponseEntity<List<TeacherOutDTO>> getAllTeachers() {
        List<TeacherOutDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }



    @PutMapping("/{TCode}")
    public ResponseEntity<Teacher> updateTeacher(
            @PathVariable String TCode,
            @Valid @RequestBody TeacherInDTO teacherInDTO
    ) throws notFoundException {
        Teacher teacher = teacherService.updateTeacher(TCode, teacherInDTO);
        return ResponseEntity.ok(teacher);
    }

    @Operation(
            summary = "Delete Teacher",
            description = "Delete Student by the Matricule"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @DeleteMapping("/{TCode}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String TCode) throws notFoundException {
        teacherService.deleteTeacher(TCode);
        return ResponseEntity.noContent().build();
    }
}
