package com.nezam.proxy.sy.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Role")
public class Role {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "ROLE_ID") Long id;
    private String name;
//
//    @ManyToMany(mappedBy = "roles")
//    private List<User> users = new ArrayList<User>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

//    public void addUser(User user) {
//        this.users.add(user);
//        user.getRoles().add(this);
//    }
//    public void removeUser(User user) {
//        this.users.remove(user);
//        user.getRoles().remove(this);
//    }
}
