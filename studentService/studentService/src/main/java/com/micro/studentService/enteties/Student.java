package com.micro.studentService.enteties;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Student extends baseEntity {
    @Id
    @GeneratedValue
    private Integer id;


    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;


    private String matricule ;

}
