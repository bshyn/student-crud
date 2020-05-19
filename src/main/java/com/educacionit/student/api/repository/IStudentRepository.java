

package com.educacionit.student.api.repository;


import com.educacionit.student.api.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, String> {
}