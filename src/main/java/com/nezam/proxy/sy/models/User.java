package com.nezam.proxy.sy.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "USERS")
public class User {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "USER_ID") Long  id;

    @NotEmpty()
    private @Column(name = "USERNAME",nullable = false,unique = false) String  username;

    private @Column(name = "PASSWORD") String  password;
    @NotEmpty()
    private @Column(name = "FIRST_NAME") String  firstName;
    @NotEmpty()
    private @Column(name = "lAST_NAME") String  lastName;
    private @Column(name = "DATE_OF_BIRTH")
    LocalDate dateOfBirth;
    @Email
    private @Column(name = "EMAIL") String email;
    @Lob
    @Column(name="PROFILE_PICTURE")
    private byte[] profilePicture;

    private Boolean enabled = true;

    @ManyToMany(cascade={PERSIST, DETACH},fetch = FetchType.EAGER)
    @JoinTable(
            name = "ROLE_USER",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
    }

    public User(Long id, String username, String password, String firstName, String lastName, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
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

        this.password =  password;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
