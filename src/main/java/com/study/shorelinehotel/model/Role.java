package com.study.shorelinehotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<User> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public void assignRoleToUser(User user) {
        user.getRoles().add(this);
        this.users.add(user);
    }

    public void removeUserFromRole(User user) {
        user.getRoles().remove(this);
        this.users.remove(user);
    }

    public void removeAllUsersFromRole() {
        if (getUsers() != null){
            List<User> roleUsers = getUsers().stream().toList();
            roleUsers.forEach(this::removeUserFromRole);
        }
    }

    public String getName(){
        return name != null ? name : "";
    }
}
