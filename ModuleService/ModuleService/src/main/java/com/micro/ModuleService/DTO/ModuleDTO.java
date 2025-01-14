package com.micro.ModuleService.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
        name = "Module_Post"
)
public class ModuleDTO {

    @Schema(
            example = "INFO1"
    )
    @NotEmpty(message = "Module code must not be empty")
    @Size(min = 3, max = 10, message = "Module code must be between 3 and 10 characters")
    private String code;

    @Schema(
            example = "Informatique"
    )
    @NotEmpty(message = "Module name must not be empty")
    @Size(min = 3, max = 50, message = "Module name must be between 3 and 50 characters")
    private String name;

    @Size(max = 255, message = "Module description must not exceed 255 characters")
    private String description;

    private String teacherId;
}
