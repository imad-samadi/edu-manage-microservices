package com.imad.springAuthServer.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class client extends baseEntity {
    @Id
    @GeneratedValue
    private Integer id ;

    private String email ;

    @Column(nullable = false)
    private String userName ;

    @Column(nullable = false)
    private String password ;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "client_Authoroties",joinColumns = @JoinColumn(name = "client_id"))
    private List<String> authoroties ;


}
