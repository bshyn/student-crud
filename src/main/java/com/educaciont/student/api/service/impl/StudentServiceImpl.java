
package com.educaciont.student.api.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.educaciont.student.api.entity.StudentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.educaciont.student.api.model.StudentModel;
import com.educaciont.student.api.repository.IStudentRepository;
import com.educaciont.student.api.service.IStudentService;
import org.springframework.stereotype.Service;


@Service ("studentServiceBean")
public class StudentServiceImpl implements IStudentService<StudentModel> {


    @Autowired
    private IStudentRepository repository;


    @Override
    public List<StudentModel> findAll () {

        List<StudentModel> list = this.repository.
                findAll().
                stream ().
                map ((e) -> new StudentModel (e.getName(), e.getLastName(), e.getDni(), e.getEmail(), e.getMobile())).
                collect(Collectors.toList ());

        return list;
    }

    @Override
    public StudentModel findByDni (String dni) {

        StudentEntity entity = this.repository.findById(dni).get();

        if (entity != null) {

            return new StudentModel (entity.getName(), entity.getLastName(), entity.getDni(),
                                     entity.getEmail(), entity.getMobile());
        } else {

            return null;
        }
    }

    @Override
    public void create (StudentModel model) {

        StudentEntity entity = new StudentEntity ();

        BeanUtils.copyProperties (model, entity);

        this.repository.save (entity);
    }

    @Override
    public void update (StudentModel model) {

        StudentEntity entity = this.repository.findById (model.getDni()).get();

        BeanUtils.copyProperties (model, entity);

        this.repository.save (entity);
    }

    @Override
    public void delete (String dni) {

        this.repository.deleteById(dni);
    }
}