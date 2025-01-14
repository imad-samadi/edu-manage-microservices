package com.micro.TeacherService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Schema(
        name = "RESPONSE SCHEMA"
)
public class responseDTO {

private String statusCode ;

private String statusMsg ;



}
