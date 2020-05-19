
package com.educacionit.student.api.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.educacionit.student.api.model.StudentModel;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity (name="Student")
@Table (name="STUDENTS")
public class StudentEntity {

    @Id
    @Size(min = 5, max = 15)
    private String dni;

    @NotNull
    @Column(name = "FIRST_NAME", length = 30)
    private String name;

    @NotNull
    @Column(name = "LAST_NAME", length = 30)
    private String lastName;

    @Size(min = 5, max = 50)
    private String email;

    @NotNull
    @Size(min = 4, max = 20)
    private String mobile;

    public StudentEntity(StudentModel model){
        this.dni = model.getDni();
        this.name = model.getName();
        this.lastName = model.getLastName();
        this.email = model.getEmail();
        this.mobile = model.getMobile();
    }
}