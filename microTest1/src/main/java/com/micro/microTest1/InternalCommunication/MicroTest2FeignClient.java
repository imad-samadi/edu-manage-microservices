package com.micro.microTest1.InternalCommunication;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "MICROTEST2", fallback = MicroTest2FeignClientFallback.class)
public interface MicroTest2FeignClient {

    @GetMapping(value = "/api/testCommuni", consumes = "application/json")
    messageDto getMessage();
}




