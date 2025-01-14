package com.micro.TeacherService.entetites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Teacher extends baseEntity{

    @Id
    @GeneratedValue
    private Integer id;


    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String TCode ; //Teacher Code

    private String specialization;

}
