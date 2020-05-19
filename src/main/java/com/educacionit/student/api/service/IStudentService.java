
package com.educacionit.student.api.service;


import com.educacionit.student.api.exception.ConflictException;
import com.educacionit.student.api.exception.NotFoundException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;


public interface IStudentService<T> {

    List<T> findAll ();

    T findByDni (String dni);

    void create (T model);

    void update (T model);

    void delete (String dni);
}