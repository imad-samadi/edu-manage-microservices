package com.micro.ModuleService.entities;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Module extends baseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Auto-generated ID


    private String code; // Unique identifier (e.g., "info1")


    private String name;


    private String description;

    private String teacherId; // ID of the assigned teacher
}
