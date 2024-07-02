package com.study.shorelinehotel.service;

import com.study.shorelinehotel.exception.AlreadyExistsException;
import com.study.shorelinehotel.model.Role;
import com.study.shorelinehotel.model.User;
import com.study.shorelinehotel.repository.RoleRepository;
import com.study.shorelinehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new AlreadyExistsException(user.getEmail() + " already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

    @Transactional
    @Override
    public void deleteUserByEmail(String email) {
        User user = getUserByEmail(email);
        if (user != null){
            userRepository.deleteByEmail(email);
        }
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        User user = getUserById(id);
        if (user != null) {
            userRepository.deleteById(id);
        }
    }
}
