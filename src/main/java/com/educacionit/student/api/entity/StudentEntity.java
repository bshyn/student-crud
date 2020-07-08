
package com.educacionit.student.api.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.educacionit.student.api.model.StudentModel;
import lombok.*;


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
    @NotNull(message = "Dni shouldn't be null")
    @NotEmpty(message = "Dni shouldn't be empty")
    @Size(min = 5, max = 15)
    private String dni;

    @NotNull(message = "Name shouldn't be null")
    @NotEmpty(message = "Name shouldn't be empty")
    @Column(name = "FIRST_NAME", length = 30)
    private String name;

    @NotNull(message = "Last Name shouldn't be null")
    @NotEmpty(message = "Last Name shouldn't be empty")
    @Column(name = "LAST_NAME", length = 30)
    private String lastName;

    @Size(min = 5, max = 50)
    private String email;

    @NotNull(message = "Mobile shouldn't be null")
    @NotEmpty(message = "Mobile shouldn't be empty")
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