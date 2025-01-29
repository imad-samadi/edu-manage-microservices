package com.micro.studentService.controllers;


import com.micro.studentService.DTO.StudentDetailsDTO;
import com.micro.studentService.DTO.StudentInDTO;
import com.micro.studentService.enteties.Student;
import com.micro.studentService.exceptions.notFoundException;
import com.micro.studentService.exceptions.studentExists;
import com.micro.studentService.services.IStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Validated
@AllArgsConstructor
public class StudentController {


    private IStudentService studentService;

    @Operation(
            summary = "CREATE Student",
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
    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody StudentInDTO studentInDTO)  {
        Student student = studentService.addStudent(studentInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // Get a student by matricule
    @GetMapping("/{matricule}")
    public ResponseEntity<StudentDetailsDTO> getStudentByMatricule(@PathVariable String matricule) {
        StudentDetailsDTO  student = studentService.getStudentByMatricule(matricule);
        return ResponseEntity.ok(student);
    }

    @Operation(
            summary = "Update Student ",
            description = "Update by the Matricule"
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
    @PutMapping("/{matricule}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable String matricule,
            @Valid @RequestBody StudentInDTO studentInDTO
    )  {
        Student student = studentService.updateStudent(matricule, studentInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @Operation(
            summary = "Delete Student",
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
    @DeleteMapping("/{matricule}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String matricule)  {
        studentService.deleteStudent(matricule);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/studentExists/{matr}")
    public ResponseEntity<Boolean> studentExists(@PathVariable String matr) {
        this.studentService.checkStudentExists(matr) ;
        return ResponseEntity.ok(true);
    }
}
