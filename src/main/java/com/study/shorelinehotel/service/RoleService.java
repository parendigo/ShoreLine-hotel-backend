package com.study.shorelinehotel.service;

import com.study.shorelinehotel.exception.AlreadyExistsException;
import com.study.shorelinehotel.exception.ResourceNotFoundException;
import com.study.shorelinehotel.model.Role;
import com.study.shorelinehotel.model.User;
import com.study.shorelinehotel.repository.RoleRepository;
import com.study.shorelinehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role role) {
        String roleName = "ROLE_" + role.getName().toUpperCase();
        Role newRole = new Role(roleName);
        if (roleRepository.existsByName(newRole.getName())){
            throw new AlreadyExistsException(newRole.getName() + " role already exists");
        }
        return roleRepository.save(newRole);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name).get();
    }

    @Override
    public void deleteRoleById(Long roleId) {
        this.removeAllUsersFromRole(roleId);
        roleRepository.deleteById(roleId);
    }

    @Override
    public void deleteRoleByName(String name) {

    }

    @Transactional
    @Override
    public User removeUserFromRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(user.get())) {
            role.get().removeUserFromRole(user.get());
            roleRepository.save(role.get());
        } else throw new ResourceNotFoundException("User with id "
                + userId + " or Role with id " + roleId + " not found");
        return user.get();
    }

    @Transactional
    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent() && !role.get().getUsers().contains(user.get())) {
            role.get().assignRoleToUser(user.get());
            roleRepository.save(role.get());
        } else throw new AlreadyExistsException("User with id " + userId + " already assigned to Role with id " + roleId);
        return user.get();
    }

    @Override
    public Role removeAllUsersFromRole(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        role.get().removeAllUsersFromRole();
        return roleRepository.save(role.get());
    }
}
