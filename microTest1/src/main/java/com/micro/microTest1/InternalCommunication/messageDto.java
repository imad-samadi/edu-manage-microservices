package com.micro.microTest1.InternalCommunication;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class messageDto {

    private String from  ;
    private String message ;
}
