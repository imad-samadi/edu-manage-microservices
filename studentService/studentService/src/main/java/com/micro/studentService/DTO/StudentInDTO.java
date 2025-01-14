package com.micro.studentService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;

@Data
@Builder
@Schema(
        name = "Student_Post"
)
public class StudentInDTO {

    @Schema(
            example = "imad"
    )
    @NotEmpty(message = "firstName must not empty")
    @Size(min = 3 , max = 10 ,message = "the length should be between 5 and 10 ")
    private String firstName;

    @Schema(
            example = "samadi"
    )
    @NotEmpty(message = "lastName must not empty")
    @Size(min = 3 , max = 10 ,message = "the length should be between 5 and 10 ")
    private String lastName;

    @jakarta.validation.constraints.NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    @Schema(
            description = "Email of the user", example = "imadsamadi@gmail.com"
    )
    private String email ;

    @Schema(
            example = "2000-01-15"
    )
    @NotNull(message = "Date of birth must not be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth ;

    @NotEmpty(message = "matricule must not empty")
    @Size(min = 3 , max = 10 ,message = "the length should be between 5 and 10 ")

    private String matricule ;




}
