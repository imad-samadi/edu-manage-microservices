package com.micro.studentService.services.impl;

import com.micro.studentService.DTO.StudentInDTO;
import com.micro.studentService.DTO.responseDTO;
import com.micro.studentService.DTO.studentAuthDTO;
import com.micro.studentService.enteties.Student;
import com.micro.studentService.exceptions.notFoundException;
import com.micro.studentService.exceptions.studentExists;
import com.micro.studentService.repo.studentRepo;
import com.micro.studentService.services.AuthServiceClient;
import com.micro.studentService.services.IStudentService;
import com.micro.studentService.services.PasswordGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private studentRepo studentRepository;


    private AuthServiceClient authServiceClient;


    @Override
    public Student addStudent(StudentInDTO studentInDTO) throws studentExists {
        // Check if a student with the same matricule already exists
        if (studentRepository.findByMatricule(studentInDTO.getMatricule()).isPresent()) {
            throw new studentExists("A student with the matricule " + studentInDTO.getMatricule() + " already exists.");
        }



        String temporaryPassword = PasswordGenerator.generateRandomPassword(9);


        studentAuthDTO authDTO = studentAuthDTO.builder()
                .email(studentInDTO.getEmail())
                .password(temporaryPassword)
                .authoroties(List.of("STUDENT"))
                .userName(studentInDTO.getMatricule())
                .build();



        ResponseEntity<responseDTO> response = authServiceClient.registerUser(authDTO);

        if (response.getStatusCode() != HttpStatus.CREATED || response.getBody() == null) {
            throw new RuntimeException("Failed to create user in Auth Service");
        }



        //Save the student in the Student Service
        Student student = new Student();
        student.setFirstName(studentInDTO.getFirstName());
        student.setLastName(studentInDTO.getLastName());
        student.setCreatedAt(LocalDateTime.now());

        student.setDateOfBirth(studentInDTO.getDateOfBirth());
        student.setMatricule(studentInDTO.getMatricule());


        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByMatricule(String matricule) throws notFoundException {
        return studentRepository.findByMatricule(matricule)
                .orElseThrow(() -> new notFoundException("Student not found with matricule: " + matricule));
    }

    @Override
    public Student updateStudent(String matricule, StudentInDTO studentInDTO) throws notFoundException {
        Student student = studentRepository.findByMatricule(matricule)
                .orElseThrow(() -> new notFoundException("Student not found with matricule: " + matricule));

        student.setFirstName(studentInDTO.getFirstName());
        student.setLastName(studentInDTO.getLastName());
        student.setUpdatedAt(LocalDateTime.now());

        student.setDateOfBirth(studentInDTO.getDateOfBirth());


        studentAuthDTO authDTO = studentAuthDTO.builder()
                .email(studentInDTO.getEmail())

                .build();

        ResponseEntity<Void> response = authServiceClient.updateUser(matricule, authDTO);

        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Failed to update user in Auth Service");
        }

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(String matricule) throws notFoundException {
        Student student = studentRepository.findByMatricule(matricule)
                .orElseThrow(() -> new notFoundException("Student not found with matricule: " + matricule));

        ResponseEntity<Void> response = authServiceClient.deleteUser(matricule);
        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Failed to delete user in Auth Service");
        }

        studentRepository.delete(student);
    }
    @Override
    public void checkStudentExists(String matricule) {
        this.studentRepository.findByMatricule(matricule).orElseThrow(() -> new studentExists("Student not found with matricule: " + matricule));
    }
}
