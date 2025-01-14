package com.micro.TeacherService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;

@Data
@Builder
@Schema(
        name = "Teacher_Post"
)
public class TeacherInDTO {

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



    @NotEmpty(message = "specialization must not empty")
    @Size(min = 3 , max = 10 ,message = "the length should be between 5 and 10 ")
    private String specialization;
}
