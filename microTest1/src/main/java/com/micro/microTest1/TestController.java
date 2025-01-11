package com.micro.microTest1;

import com.micro.microTest1.InternalCommunication.MicroTest2FeignClient  ;
import com.micro.microTest1.InternalCommunication.messageDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api")

public class TestController {

    private final studentContactInfo microTest1ContactInfo ;
    private final MicroTest2FeignClient microTest2FeignClient;

    public TestController(com.micro.microTest1.studentContactInfo microTest1ContactInfo, MicroTest2FeignClient microTest2FeignClient) {
        this.microTest1ContactInfo = microTest1ContactInfo;
        this.microTest2FeignClient = microTest2FeignClient;
    }

    @GetMapping("/GETApiContact")
    public ResponseEntity<studentContactInfo> get(){
        return ResponseEntity.status(200)
                .body(this.microTest1ContactInfo);

    }

    @GetMapping("/communication")
    public messageDto getMessage(){
        return this.microTest2FeignClient.getMessage() ;
    }
}
