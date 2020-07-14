package com.educacionit.student.api.service.impl;

import com.educacionit.student.api.entity.RoleEntity;
import com.educacionit.student.api.entity.UserEntity;
import com.educacionit.student.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null){
            throw new UsernameNotFoundException(username);
        }

        return build(userEntity);
    }

    private UserDetails build(UserEntity userEntity){
        return new User(userEntity.getUsername(), userEntity.getPassword(), getAuthorities(userEntity));
    }

    public static Set<? extends GrantedAuthority> getAuthorities(UserEntity userEntity){
        Set<RoleEntity> roles = userEntity.getRoles();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));

        return authorities;
    }
}
