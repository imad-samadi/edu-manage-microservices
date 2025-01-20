package com.micro.studentService.services;
import com.micro.studentService.DTO.StudentDetailsDTO;
import com.micro.studentService.DTO.StudentInDTO;
import com.micro.studentService.enteties.Student;
import com.micro.studentService.exceptions.notFoundException;
import com.micro.studentService.exceptions.studentExists;

import java.util.List;

public interface IStudentService {

    Student addStudent(StudentInDTO studentInDTO) throws studentExists;
    List<Student> getAllStudents();
    StudentDetailsDTO getStudentByMatricule(String matricule) throws notFoundException;
    Student updateStudent(String matricule, StudentInDTO studentInDTO) throws notFoundException;
    void deleteStudent(String matricule) throws notFoundException;

    void checkStudentExists(String matr);
}
