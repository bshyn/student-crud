package com.educacionit.student.api.service.impl;

import com.educacionit.student.api.entity.UserEntity;
import com.educacionit.student.api.repository.IUserRepository;
import com.educacionit.student.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService<UserEntity> {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void save(UserEntity user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addUserRole(long userId) {
        userRepository.addUserRole(userId);
    }

    @Override
    public void addAdminRole(long userId) {
        userRepository.addAdminRole(userId);
    }
}
