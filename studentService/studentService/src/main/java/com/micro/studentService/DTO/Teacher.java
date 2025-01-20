package com.micro.studentService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
        name = "Teacher_GET"
)
public class Teacher {


    private String firstName;


    private String lastName;


    private String specialization;


    private String tcode;

}
