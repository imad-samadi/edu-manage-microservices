package com.micro.studentService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Schema(
        name = "Student_Get"
)
public class StudentDetailsDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String matricule;
    private List<ModuleOutDTO> modules;
}
