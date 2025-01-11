package com.micro.microTest1.InternalCommunication;

import org.springframework.stereotype.Component;

@Component
public class MicroTest2FeignClientFallback implements MicroTest2FeignClient{
    @Override
    public messageDto getMessage() {

        return messageDto.builder().message(null)
                .from(null)
                .build();
    }
}
