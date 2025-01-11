package com.imad.springAuthServer.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "authserver")
@Getter
@Setter
public class authServerInfo {

    private String message ;
    private Map<String,String> contactDetails ;
    private String onCallSupport ;

}
