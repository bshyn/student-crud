
package com.educacionit.student.api.entity;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
@Table (name="STUDENT")
public class StudentEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Dni shouldn't be empty")
    @Size(min = 5, max = 15)
    private String dni;

    @NotEmpty(message = "Name shouldn't be empty")
    @Column(name = "FIRST_NAME", length = 30)
    private String name;

    @NotEmpty(message = "Last Name shouldn't be empty")
    @Column(name = "LAST_NAME", length = 30)
    private String lastName;

    @Size(min = 5, max = 50)
    private String email;

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