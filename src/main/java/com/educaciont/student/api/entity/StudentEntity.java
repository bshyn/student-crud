
package com.educaciont.student.api.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity (name="Student")
@Table (name="student")
public class StudentEntity {

    @Id
    private String dni;

    private String name;

    private String lastName;

    private String email;

    private String mobile;
}