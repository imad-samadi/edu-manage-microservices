package com.micro.TeacherService.services.impl;

import com.micro.TeacherService.DTO.TeacherAuthDTO;
import com.micro.TeacherService.DTO.TeacherInDTO;
import com.micro.TeacherService.DTO.TeacherOutDTO;
import com.micro.TeacherService.DTO.responseDTO;
import com.micro.TeacherService.entetites.Teacher;
import com.micro.TeacherService.exceptions.notFoundException;
import com.micro.TeacherService.repo.TeacherRepo;
import com.micro.TeacherService.services.AuthServiceClient;
import com.micro.TeacherService.services.ITeacherService;
import com.micro.TeacherService.services.ModuleServiceClient;
import com.micro.TeacherService.services.PasswordGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.micro.TeacherService.DTO.Module ;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service

@Transactional
public class TeacherServiceImpl implements ITeacherService {
    private TeacherRepo teacherRepository;

    private AuthServiceClient authServiceClient;
    private ModuleServiceClient moduleServiceClient;

    public TeacherServiceImpl(TeacherRepo teacherRepository, AuthServiceClient authServiceClient, ModuleServiceClient moduleServiceClient) {
        this.teacherRepository = teacherRepository;
        this.authServiceClient = authServiceClient;
        this.moduleServiceClient = moduleServiceClient;
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Override

    public Teacher addTeacher(TeacherInDTO teacherInDTO) {

        String temporaryPassword = PasswordGenerator.generateRandomPassword(8);
        // Fetch the next sequence value.
        Long nextId = (Long) entityManager.createNativeQuery("SELECT nextval('teacher_id_seq')").getSingleResult();

        TeacherAuthDTO authDTO = TeacherAuthDTO.builder()
                .email(teacherInDTO.getEmail())
                .password(temporaryPassword)
                .userName(String.format("TID-%06d", nextId))
                .authoroties(List.of("TEACHER"))
                .build();
        ResponseEntity<responseDTO> response = authServiceClient.registerUser(authDTO);

        if (response.getStatusCode() != HttpStatus.CREATED || response.getBody() == null) {
            throw new RuntimeException("Failed to create user in Auth Service");
        }
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherInDTO.getFirstName());
        teacher.setLastName(teacherInDTO.getLastName());
        teacher.setCreatedAt(LocalDateTime.now());
        teacher.setTCode(String.format("TID-%06d", nextId));
        teacher.setSpecialization(teacherInDTO.getSpecialization());
        return teacherRepository.save(teacher);
    }

    @Override
    public List<TeacherOutDTO> getAllTeachers() {

            // Step 1: Fetch all teachers
            List<Teacher> teachers = teacherRepository.findAll();

            // Step 2: For each teacher, fetch their modules and map to TeacherOutDTO
            return teachers.stream()
                    .map(teacher -> {
                        List<Module> modules = moduleServiceClient.getModulesByTeacherId(teacher.getTCode());
                        return TeacherOutDTO.builder()
                                .firstName(teacher.getFirstName())
                                .lastName(teacher.getLastName())
                                .specialization(teacher.getSpecialization())
                                .TCode(teacher.getTCode())
                                .modules(modules)
                                .build();
                    })
                    .collect(Collectors.toList());

    }

    @Override
    public TeacherOutDTO getTeacherWithModules(String TCode) throws notFoundException {

        Teacher teacher = teacherRepository.findByTCode(TCode)
                .orElseThrow(() -> new notFoundException("Teacher not found with TCode: " + TCode));


        List<Module> modules = moduleServiceClient.getModulesByTeacherId(TCode);


        return TeacherOutDTO.builder()
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .specialization(teacher.getSpecialization())
                .TCode(teacher.getTCode())
                .modules(modules)
                .build();
    }

   @Override
    @Transactional
    public Teacher updateTeacher(String TCode, TeacherInDTO teacherInDTO) throws notFoundException {
        Teacher teacher = teacherRepository.findByTCode(TCode)
                .orElseThrow(() -> new notFoundException("Teacher not found with TCode: " + TCode));
        teacher.setFirstName(teacherInDTO.getFirstName());
        teacher.setLastName(teacherInDTO.getLastName());
        teacher.setSpecialization(teacherInDTO.getSpecialization());
        teacher.setUpdatedAt(LocalDateTime.now());
        return teacherRepository.save(teacher);

    }

    @Override
    public void deleteTeacher(String TCode)  {
        Teacher teacher = teacherRepository.findByTCode(TCode)
                .orElseThrow(() -> new notFoundException("Teacher not found with TCode: " + TCode));


        ResponseEntity<Void> response = authServiceClient.deleteUser(TCode);

        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Failed to delete user in Auth Service");
        }
        teacherRepository.delete(teacher);
    }

}
