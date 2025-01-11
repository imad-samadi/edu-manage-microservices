package com.imad.springAuthServer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class baseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt ;


    @LastModifiedDate
    @Column(insertable = false, updatable = true)
    private LocalDateTime updatedAt ;
}
