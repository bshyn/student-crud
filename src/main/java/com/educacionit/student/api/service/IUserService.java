package com.educacionit.student.api.service;

public interface IUserService<T> {

    void save(T user);

    T findByUsername(String username);

    void addUserRole(long userId);

    void addAdminRole(long userId);

}
