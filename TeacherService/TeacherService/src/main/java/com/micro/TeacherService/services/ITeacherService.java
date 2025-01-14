package com.micro.TeacherService.services;

import com.micro.TeacherService.DTO.TeacherInDTO;
import com.micro.TeacherService.DTO.TeacherOutDTO;
import com.micro.TeacherService.entetites.Teacher;
import com.micro.TeacherService.exceptions.notFoundException;
import com.micro.TeacherService.DTO.Module ;
import java.util.List;

public interface ITeacherService {
    Teacher addTeacher(TeacherInDTO teacherInDTO);
    List<TeacherOutDTO> getAllTeachers();
    TeacherOutDTO getTeacherWithModules(String TCode) throws notFoundException;
    Teacher updateTeacher(String TCode, TeacherInDTO teacherInDTO) throws notFoundException;
    void deleteTeacher(String TCode) throws notFoundException;
}
