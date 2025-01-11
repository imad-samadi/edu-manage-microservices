package com.imad.springAuthServer.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Data
@Builder
@Schema(
        name = "User",
        description = "Schema to hold User information"
)
public class UserDTO {

    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    @Schema(
            description = "Email of the user", example = "imadsamadi@gmail.com"
    )
    private String email ;

    @NotEmpty(message = "userName can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    @Schema(
            description = "UserName of the user", example = "imad"
    )
    private String userName ;


    @NotEmpty(message = "Password can not be a null or empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character (@#$%^&+=), and no whitespace"
    )
    @Schema(
            description = "Password of the user", example = "imadSamadi@32100"
    )
    private String password ;

    @NotEmpty(message = "Authoroties can not be a null or empty")
    private List<String> authoroties ;

}
