package com.micro.microTest2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "microtest2")
@Getter
@Setter
public class microTest2ContactInfo {

    private String message ;
    private Map<String,String>contactDetails ;
    private String onCallSupport ;

}
