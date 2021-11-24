package com.nezam.proxy.sy.domains;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "USERS")
public class User {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "USER_ID") Long  id;
    @NotBlank(message = "Username is mandatory")
    private @Column(name = "USERNAME",nullable = false,unique = true) String  username;
    @NotBlank(message = "Password is mandatory")
    private @Column(name = "PASSWORD") String  password;

    private @Column(name = "FIRST_NAME") String  firstName;
    private @Column(name = "lAST_NAME") String  lastName;
    public User() {
    }

    public User(Long id, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
