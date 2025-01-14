package com.micro.TeacherService.repo;

import com.micro.TeacherService.entetites.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

public interface TeacherRepo extends JpaRepository<Teacher,Integer>{

    Optional<Teacher> findByTCode(String TCode);
}
