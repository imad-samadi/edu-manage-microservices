package com.imad.springAuthServer.service;

import com.imad.springAuthServer.DTO.UserDTO;
import com.imad.springAuthServer.entities.client;

public interface IUserService {
        void addUser(UserDTO userDTO  ) ;

        UserDTO getUserInfo(String userName) ;

        void deleteUser(String userName) ;

        void update(String userName , UserDTO userDTO ) ;


}
