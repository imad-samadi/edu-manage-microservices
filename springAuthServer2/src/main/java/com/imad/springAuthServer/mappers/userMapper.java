package com.imad.springAuthServer.mappers;

import com.imad.springAuthServer.DTO.UserDTO;
import com.imad.springAuthServer.entities.client;

import java.time.LocalDateTime;

public class userMapper {
    public static UserDTO convertToDto(client client){

            return UserDTO.builder()
                    .userName(client.getUserName())
                    .email(client.getEmail())
                    .password(null)
                    .authoroties(client.getAuthoroties())
                    .build() ;

    }

    public static client convertToClient(UserDTO userDTO){

        return client.builder()
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .authoroties(userDTO.getAuthoroties())
                .createdAt(LocalDateTime.now())
                .build() ;
    }
}
