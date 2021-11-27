package com.nezam.proxy.sy.services.impl;

import com.nezam.proxy.sy.models.User;
import com.nezam.proxy.sy.repository.UserRepository;
import com.nezam.proxy.sy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public ResponseEntity<List<User>> findAll() {
        List<User> objects = repository.findAll();
        return  ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<User> findById(Long id) {
        Optional<User> foundObject = repository.findById(id);
        if(foundObject.isPresent())
            return ResponseEntity.ok(foundObject.get());
        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<User> create(User obj) {
        User createdObject = repository.save(obj);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdObject.getId()).toUri();
        return ResponseEntity.created(location).body(createdObject);
    }

    @Override
    public ResponseEntity<User> update(User obj,Long id) {
        Optional<User> foundObject = repository.findById(id);
        if(!foundObject.isPresent())
            return ResponseEntity.notFound().build();

        obj.setId(id);
        User updatedObject = repository.save(obj);
        return ResponseEntity.ok(updatedObject);
    }

    @Override
    public ResponseEntity delete(Long id) {
        Optional<User> foundObject = repository.findById(id);
        if(!foundObject.isPresent())
            return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
