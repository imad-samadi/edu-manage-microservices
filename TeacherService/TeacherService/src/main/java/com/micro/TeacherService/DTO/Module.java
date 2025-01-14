package com.micro.TeacherService.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Module {
    private String code;
    private String name;
    private String description;
}
