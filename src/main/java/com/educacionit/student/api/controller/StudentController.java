
package com.educacionit.student.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.educacionit.student.api.model.StudentModel;
import com.educacionit.student.api.service.IStudentService;


@RestController
@RequestMapping ("students")
public class StudentController {


    @Autowired
    private IStudentService<StudentModel> service;


    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<?> getAll () {

        List<StudentModel> list = this.service.findAll();

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok (list);
        }
    }

    @RequestMapping (value = "/{dni}", method = RequestMethod.GET)
    public ResponseEntity<?> getByDni (@PathVariable("dni") String dni) {
        StudentModel model = this.service.findByDni (dni);
        return ResponseEntity.ok (model);
    }

    @RequestMapping (method = RequestMethod.POST)
    public ResponseEntity<?> create (@RequestBody StudentModel model) {
        this.service.create (model);
        return new ResponseEntity (model, HttpStatus.CREATED);
    }

    @RequestMapping (
            value = "/{dni}", method = RequestMethod.PUT)
    public ResponseEntity<?> update (@PathVariable("dni") String dni, @RequestBody StudentModel model) {
        this.service.update (model);
        return ResponseEntity.ok(model);
    }

    @RequestMapping (value = "/{dni}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete (@PathVariable("dni") String dni) {
        this.service.delete (dni);
        return ResponseEntity.noContent().build();
    }
}