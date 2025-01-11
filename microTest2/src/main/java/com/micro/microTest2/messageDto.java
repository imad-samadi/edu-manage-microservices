package com.micro.microTest2;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class messageDto {

    private String from  ;
    private String message ;
}
