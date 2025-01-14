package com.micro.studentService.contactApi;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api")

public class ContactController {

    private final studentContactInfo microTest1ContactInfo ;
    //private final MicroTest2FeignClient microTest2FeignClient;

    public ContactController(studentContactInfo studentContactInfo ) {
        this.microTest1ContactInfo = studentContactInfo ;
       // this.microTest2FeignClient = microTest2FeignClient;
    }
    @Operation(
            summary = "Student server contact info",
            description = "contact info in case of a question or problem "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200"

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @GetMapping("/GETApiContact")
    public ResponseEntity<studentContactInfo> get(){
        return ResponseEntity.status(200)
                .body(this.microTest1ContactInfo);

    }

   /* @GetMapping("/communication")
    public messageDto getMessage(){
        return this.microTest2FeignClient.getMessage() ;
    }*/
}
