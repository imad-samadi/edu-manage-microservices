package com.micro.studentService.repo;

import com.micro.studentService.enteties.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface studentRepo extends JpaRepository<Student, Integer> {

    Optional<Student> findByMatricule(String matricule);
    void deleteByMatricule(String matricule);
}
