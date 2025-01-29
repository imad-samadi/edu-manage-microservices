package com.imad.springAuthServer.controllers;

import com.imad.springAuthServer.DTO.UserDTO;
import com.imad.springAuthServer.DTO.responseDTO;
import com.imad.springAuthServer.service.EmailService;
import com.imad.springAuthServer.service.impl.userService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.imad.springAuthServer.DTO.authServerInfo ;

@RestController
@AllArgsConstructor

@Tag(
        name = "CRUD REST APIS for Users",
        description = "CRUD Rest API to CREATE,UPDATE,FETCH AND DELETE Users "
)
@Validated
@RequestMapping("/api")
public class AddClientConroller {

    private authServerInfo authServerInfo ;
    private PasswordEncoder passwordEncoder  ;
    private userService  userService ;
    private final EmailService emailService;


    @Operation(
            summary = "CREATE User",
            description = "CREATING NEW USER"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @PostMapping("/user/add")
    public ResponseEntity<responseDTO> addUser(@Valid @RequestBody UserDTO userDTO ) {
        String pass = userDTO.getPassword();
        userDTO.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));

        // Add the user
        this.userService.addUser(userDTO);

        // Send an email with credentials
        try {
            emailService.sendCredentialsEmail(userDTO.getEmail(), userDTO.getUserName(), pass);
        } catch (MailException e) {
            // Log the error and continue
            System.err.println("Failed to send email: " + e.getMessage());
        }

        return ResponseEntity.status(201)
                .body(
                        responseDTO.builder()
                                .statusCode("201")
                                .statusMsg("User created")
                                .build()
                );


    }

    @Operation(
            summary = "Get User Info ",
            description = "Get User Info by the UserName"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @GetMapping("/user")
    public UserDTO getUserInfo(@RequestParam String userName){

        return this.userService.getUserInfo(userName) ;
    }

    @Operation(
            summary = "Delete User ",
            description = "Delete User by the UserName"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @DeleteMapping("/user/delete/{userName}")
    public ResponseEntity<responseDTO> deleteUser(@PathVariable String userName){
        this.userService.deleteUser(userName);

        return  ResponseEntity.status(201)
                .body(
                        responseDTO.builder()
                                .statusCode("201")
                                .statusMsg("User deleted")
                                .build()
                ) ;

    }

    @Operation(
            summary = "Update User ",
            description = "Update by the UserName"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @PutMapping("/user/update/{userName}")
    public ResponseEntity<responseDTO> update(@PathVariable String userName,@RequestBody UserDTO userDTO){

       // userDTO.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        this.userService.update(userName,userDTO);
        return  ResponseEntity.status(201)
                .body(
                        responseDTO.builder()
                                .statusCode("201")
                                .statusMsg("User updated")
                                .build()
                ) ;

    }

    @Operation(
            summary = "Auth server contact info",
            description = "contact info "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @GetMapping("/GETApiContact")
    public ResponseEntity<authServerInfo> get(){
        return ResponseEntity.status(200)
                .body(this.authServerInfo);

    }

}

