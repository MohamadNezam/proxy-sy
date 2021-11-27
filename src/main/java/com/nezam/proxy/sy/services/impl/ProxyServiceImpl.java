package com.nezam.proxy.sy.services.impl;

import com.nezam.proxy.sy.models.Proxy;
import com.nezam.proxy.sy.models.User;
import com.nezam.proxy.sy.repository.ProxyRepository;
import com.nezam.proxy.sy.repository.UserRepository;
import com.nezam.proxy.sy.services.ProxyService;
import com.nezam.proxy.sy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ProxyServiceImpl implements ProxyService {

    @Autowired
    private ProxyRepository repository;

    @Override
    public ResponseEntity<List<Proxy>> findAll() {
        List<Proxy> objects = repository.findAll();
        return  ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<Proxy> findById(Long id) {
        Optional<Proxy> foundObject = repository.findById(id);
        if(foundObject.isPresent())
            return ResponseEntity.ok(foundObject.get());
        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<Proxy> create(Proxy obj) {
        Proxy createdObject = repository.save(obj);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdObject.getId()).toUri();
        return ResponseEntity.created(location).body(createdObject);
    }

    @Override
    public ResponseEntity<Proxy> update(Proxy obj,Long id) {
        Optional<Proxy> foundObject = repository.findById(id);
        if(!foundObject.isPresent())
            return ResponseEntity.notFound().build();

        obj.setId(id);
        Proxy updatedObject = repository.save(obj);
        return ResponseEntity.ok(updatedObject);
    }

    @Override
    public ResponseEntity delete(Long id) {
        Optional<Proxy> foundObject = repository.findById(id);
        if(!foundObject.isPresent())
            return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
