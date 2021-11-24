package com.nezam.proxy.sy.domains;

import javax.persistence.*;

@Entity
@Table(name = "AUTH_USER_GROUP")
public class AuthUserGroup {
    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "AUTH_USER_GROUP_ID") Long  id;
    private @ManyToOne @JoinColumn(name = "FK_USER_ID") User user;
    private @Column(name = "AUTH_GROUP") String  authGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }
}
