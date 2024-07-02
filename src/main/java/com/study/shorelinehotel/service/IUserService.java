package com.study.shorelinehotel.service;

import com.study.shorelinehotel.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    User registerUser(User user);
    List<User> getAllUsers();

    User getUserByEmail(String email);
    User getUserById(Long id);

    void deleteUserByEmail(String email);
    void deleteUserById(Long id);
}
