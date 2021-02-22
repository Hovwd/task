package com.task.task.service;

import com.task.task.domain.User;
import com.task.task.dto.UserDto;
import com.task.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userDao;

    private final PasswordEncoder bcryptEncoder;

    @Transactional()
    public User save(UserDto user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setLastName(user.getLastName());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }

    @Transactional(readOnly = true)
    public User loadUserByUsername(String userName){
        return  userDao.findByUsername(userName);
    }
}
