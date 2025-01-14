package com.micro.ModuleService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
        name = "Module_GET"
)
public class ModuleOutDTO {

    private String code;
    private String name;
    private String description;
    private Teacher teacher;
}
