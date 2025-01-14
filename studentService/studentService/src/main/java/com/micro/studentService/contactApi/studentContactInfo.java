package com.micro.studentService.contactApi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "studentserivce")
@Getter
@Setter
public class studentContactInfo {

    private String message ;
    private Map<String,String>contactDetails ;
    private String onCallSupport ;

}
