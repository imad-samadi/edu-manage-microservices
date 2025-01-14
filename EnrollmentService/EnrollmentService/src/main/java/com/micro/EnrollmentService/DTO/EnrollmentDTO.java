package com.micro.EnrollmentService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;

@Data
@Builder
public class EnrollmentDTO {


    @Schema(
            example = "STU121"
    )
    @NotEmpty(message = "studentMatr must not be empty")
    private String studentMatr;

    @Schema(
            example = "INFO1"
    )
    @NotEmpty(message = "moduleCode must not be empty")
    private String moduleCode;

    private LocalDateTime enrollmentDate;
}
