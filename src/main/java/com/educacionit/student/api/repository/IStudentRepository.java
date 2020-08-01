

package com.educacionit.student.api.repository;


import com.educacionit.student.api.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, String> {

    Optional<StudentEntity> findByDni(String dni);
}