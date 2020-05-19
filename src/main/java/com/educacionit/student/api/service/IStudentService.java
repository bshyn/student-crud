
package com.educacionit.student.api.service;


import java.util.List;


public interface IStudentService<T> {

    List<T> findAll ();

    T findByDni (String dni);

    void create (T model);

    void update (T model);

    void delete (String dni);
}