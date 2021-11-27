package com.nezam.proxy.sy.services;


import com.nezam.proxy.sy.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<List<User>> findAll();
    ResponseEntity<User> findById(Long id);
    ResponseEntity<User> create(User obj);
    ResponseEntity<User> update(User obj,Long id);
    ResponseEntity delete(Long id);
}
