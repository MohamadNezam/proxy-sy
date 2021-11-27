package com.nezam.proxy.sy.services;


import com.nezam.proxy.sy.models.Proxy;
import com.nezam.proxy.sy.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProxyService {

    ResponseEntity<List<Proxy>> findAll();
    ResponseEntity<Proxy> findById(Long id);
    ResponseEntity<Proxy> create(Proxy obj);
    ResponseEntity<Proxy> update(Proxy obj,Long id);
    ResponseEntity delete(Long id);
}
