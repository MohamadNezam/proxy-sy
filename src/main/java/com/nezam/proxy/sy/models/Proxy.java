package com.nezam.proxy.sy.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "PROXY")
public class Proxy {

    public enum proxyStatues{
        Active,
        Canceled
    }

    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "PROXY_ID") Long  id;
    private @ManyToOne @JoinColumn(name = "FK_USER_ID") User user;
    @NotEmpty()
    private @Column(name = "PROXY_IP") String  proxyIP;

    @NotEmpty()
    private @Column(name = "PROXY_PORT") String  proxyPort;

    @NotEmpty()
    private @Column(name = "PROXY_USERNAME") String  proxyUsername;

    @NotEmpty()
    private @Column(name = "PROXY_PASSWORD") String  proxyPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROXY_STATUES")
    private proxyStatues statues;

    public Proxy() {
    }

    public Proxy(User user, String proxyIP, String proxyPort, String proxyUsername, String proxyPassword,proxyStatues statues) {
        this.user = user;
        this.proxyIP = proxyIP;
        this.proxyPort = proxyPort;
        this.proxyUsername = proxyUsername;
        this.proxyPassword = proxyPassword;
        this.statues = statues;
    }


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

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public proxyStatues getStatues() {
        return statues;
    }

    public void setStatues(proxyStatues statues) {
        this.statues = statues;
    }
}
