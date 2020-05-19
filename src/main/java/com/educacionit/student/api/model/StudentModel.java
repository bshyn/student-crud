
package com.educacionit.student.api.model;


import com.educacionit.student.api.entity.StudentEntity;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class StudentModel {

    private String name;

    private String lastName;

    private String dni;

    private String email;

    private String mobile;

    public StudentModel(StudentEntity entity){
        this.dni = entity.getDni();
        this.name = entity.getName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.mobile = entity.getMobile();
    }
}