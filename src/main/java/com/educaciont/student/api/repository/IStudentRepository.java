

package com.educaciont.student.api.repository;


import com.educaciont.student.api.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, String> {
}