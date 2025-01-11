package com.imad.springAuthServer.Repo;


import com.imad.springAuthServer.entities.client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface clientRepo extends JpaRepository<client,Integer> {
    Optional<client> findByUserName (String userName ) ;
}
