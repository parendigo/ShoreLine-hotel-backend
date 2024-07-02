package com.study.shorelinehotel.service;

import com.study.shorelinehotel.model.Role;
import com.study.shorelinehotel.model.User;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();
    Role createRole(Role role);
    Role findRoleById(Long id);
    Role findRoleByName(String name);
    void deleteRoleById(Long id);
    void deleteRoleByName(String name);
    User removeUserFromRole(Long userId, Long roleId);
    User assignRoleToUser(Long userId, Long roleId);
    Role removeAllUsersFromRole(Long roleId);
}
