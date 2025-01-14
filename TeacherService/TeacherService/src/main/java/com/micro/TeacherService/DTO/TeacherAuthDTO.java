package com.micro.TeacherService.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class TeacherAuthDTO {
    private String email ;
    private String password ;
    private String userName ;
    private List<String> authoroties ;
}
