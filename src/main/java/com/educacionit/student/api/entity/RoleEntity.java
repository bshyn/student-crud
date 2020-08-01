package com.educacionit.student.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "APP_ROLE")
@Getter @Setter
@NoArgsConstructor
public class RoleEntity {

    @Id
    private int id;

    @Column(name = "ROLE_NAME")
    private String name;


}
