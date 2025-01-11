package com.micro.microTest2;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TestController {
private microTest2ContactInfo microTest2ContactInfo ;
    @GetMapping("/GETApiContact")
    public ResponseEntity<microTest2ContactInfo> get(){
        return ResponseEntity.status(200)
                .body(this.microTest2ContactInfo);

    }
    @GetMapping("/testCommuni")
    public messageDto test(){
        return messageDto.builder()
                .message("the message .......")
                .from("microTest22")
                .build();
    }
}
