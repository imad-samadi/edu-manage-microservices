package com.micro.ModuleService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.micro.ModuleService.entities.Module;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Integer>
{
    Optional<Module> findByCode(String code);
    Optional<List<Module>> findByTeacherId(String teacherId);
}
