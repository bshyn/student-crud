package com.educacionit.student.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@Getter @Setter
@NoArgsConstructor
public class RoleEntity {

    @Id
    private int id;

    private String name;


}
