
package com.educaciont.student.api.model;


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
}