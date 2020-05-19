
package com.educacionit.student.api.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.educacionit.student.api.entity.StudentEntity;
import com.educacionit.student.api.exception.ConflictException;
import com.educacionit.student.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import com.educacionit.student.api.model.StudentModel;
import com.educacionit.student.api.repository.IStudentRepository;
import com.educacionit.student.api.service.IStudentService;
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
                map ((e) -> new StudentModel (e)).
                collect(Collectors.toList ());

        return list;
    }

    @Override
    public StudentModel findByDni (String dni){

        Optional<StudentEntity> optional = this.repository.findById(dni);
         if(optional.isPresent()){
             StudentModel model = new StudentModel(optional.get());
             return model;
         } else {
             throw new NotFoundException(
                     String.format("Student with DNI=%s doesn't exists", dni));
         }
    }

    @Override
    public void create (StudentModel model){
        Optional<StudentEntity> optional = this.repository.findById(model.getDni());

        if (optional.isPresent()){
            throw new ConflictException(
                    String.format("Student with DNI=%s already exists", model.getDni()));
        } else {
            StudentEntity entity = new StudentEntity (model);
            this.repository.save (entity);
        }
    }

    @Override
    public void update (StudentModel model){
        Optional<StudentEntity> optional = this.repository.findById (model.getDni());

        if(optional.isPresent()){
            StudentEntity entity = new StudentEntity(model);
            this.repository.save (entity);
        } else {
            throw new NotFoundException(
                    String.format("Student with DNI=%s not found", model.getDni()));
        }
    }

    @Override
    public void delete (String dni){
            Optional<StudentEntity> optional = this.repository.findById(dni);

        if (optional.isPresent()){
            this.repository.deleteById(dni);
        } else {
            throw new NotFoundException(
                    String.format("Student with DNI=%s not found", dni));
        }
    }
}