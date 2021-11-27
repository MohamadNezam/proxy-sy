package com.nezam.proxy.sy.models;

import javax.persistence.*;

@Entity
@Table(name = "PROXY")
public class Proxy {
    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "PROXY_ID") Long  id;
    private @ManyToOne @JoinColumn(name = "FK_USER_ID") User user;
    private @Column(name = "PROXY_IP",nullable = false,unique = true) String  proxyIP;

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

    public String getProxyIP() {
        return proxyIP;
    }

    public void setProxyIP(String proxyIP) {
        this.proxyIP = proxyIP;
    }
}
