package com.imad.springAuthServer.service.impl;

import com.imad.springAuthServer.DTO.UserDTO;
import com.imad.springAuthServer.Repo.clientRepo;
import com.imad.springAuthServer.entities.client;
import com.imad.springAuthServer.exceptions.clientAlreadyExists;
import com.imad.springAuthServer.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.imad.springAuthServer.mappers.userMapper ;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class userService implements IUserService {
    private clientRepo  clientRepo ;

    @Override
    public void addUser(UserDTO userDTO ) {
            if(this.clientRepo.findByUserName(userDTO.getUserName()).isPresent()){
                throw new clientAlreadyExists("UserName Already exists ");
            }


            this.clientRepo.save(userMapper.convertToClient(userDTO));
    }

    @Override
    public UserDTO getUserInfo(String userName) {
       client c =  this.clientRepo.findByUserName(userName).orElseThrow(()->new UsernameNotFoundException("USER"+ userName+"NOT FOUND") ) ;
       return userMapper.convertToDto(c) ;

    }

    @Override
    public void deleteUser(String userName) {
        client c =  this.clientRepo.findByUserName(userName).orElseThrow(()->new UsernameNotFoundException("USER"+ userName+"NOT FOUND") ) ;
        this.clientRepo.delete(c);
    }

    @Override
    public void update(String userName,UserDTO userDTO) {
        client c =  this.clientRepo.findByUserName(userName).orElseThrow(()->new UsernameNotFoundException("USER "+ userName+" NOT FOUND") ) ;

        c.setEmail(userDTO.getEmail());
        //c.setUserName(userDTO.getUserName());
        //c.setPassword(userDTO.getPassword());
        //c.setAuthoroties(userDTO.getAuthoroties());
        c.setUpdatedAt(LocalDateTime.now());

        this.clientRepo.save(c) ;


    }
}
