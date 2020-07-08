
package com.educacionit.student.api.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.educacionit.student.api.entity.StudentEntity;
import com.educacionit.student.api.exception.BadRequestException;
import com.educacionit.student.api.exception.ConflictException;
import com.educacionit.student.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import com.educacionit.student.api.model.StudentModel;
import com.educacionit.student.api.repository.IStudentRepository;
import com.educacionit.student.api.service.IStudentService;
import org.springframework.stereotype.Service;

import javax.validation.*;


@Service ("studentServiceBean")
public class StudentServiceImpl implements IStudentService<StudentModel> {


    @Autowired
    private IStudentRepository repository;

    @Autowired
    private Validator validator;


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

            String violations = validateStudent(entity);

            if(violations.length() == 0){
                this.repository.save (entity);
            } else {
                throw new BadRequestException(violations);
            }
        }
    }

    @Override
    public void update (StudentModel model){
        Optional<StudentEntity> optional = this.repository.findById (model.getDni());

        if(optional.isPresent()){
            StudentEntity entity = new StudentEntity(model);

            String violations = validateStudent(entity);

            if(violations.length() == 0){
                this.repository.save (entity);
            } else {
                throw new BadRequestException(violations);
            }
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

    private String validateStudent(StudentEntity entity){
        Set<ConstraintViolation<StudentEntity>> violations = validator.validate(entity);
        String violationsStr = "";

        if(!violations.isEmpty()) {
            for (ConstraintViolation<StudentEntity> violation : violations) {
                if (violationsStr.length() == 0) {
                    violationsStr = violation.getPropertyPath().toString().toUpperCase() + ": " + violation.getMessage();
                } else {
                    violationsStr = violationsStr + "; " + violation.getPropertyPath().toString().toUpperCase() + ": " + violation.getMessage();
                }
            }
        }

        return violationsStr;
    }
}