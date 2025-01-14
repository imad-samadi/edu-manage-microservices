package com.micro.TeacherService.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class TeacherOutDTO {
    private String firstName;
    private String lastName;
    private String specialization;
    private String TCode;
    private List<Module> modules;
}
