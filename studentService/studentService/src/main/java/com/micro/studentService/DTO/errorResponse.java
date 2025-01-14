package com.micro.studentService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(
        name = "EROOR RESPONSE"
)
public class errorResponse {

    private String apiPath ;

    private HttpStatus errorCode ;

    private String  errorMsg ;

    private LocalDateTime errorTime ;
}
