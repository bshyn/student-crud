package com.educacionit.student.api.repository;

import com.educacionit.student.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    @Modifying @Transactional
    @Query(value= "INSERT INTO STUDENT_CRUD.USER_ROLE(USER_ID, ROLE_ID) VALUES (:USER_ID, 1)", nativeQuery = true)
    void addUserRole(@Param("USER_ID") long userId);

    @Modifying @Transactional
    @Query(value= "INSERT INTO STUDENT_CRUD.USER_ROLE(USER_ID, ROLE_ID) VALUES (:USER_ID, 2)", nativeQuery = true)
    void addAdminRole(@Param("USER_ID") long userId);
}
