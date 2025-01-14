package com.micro.studentService.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class studentAuthDTO {

    private String email ;
    private String password ;
    private String userName ;
    private List<String> authoroties ;

}
