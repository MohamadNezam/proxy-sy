package com.nezam.proxy.sy.controllers.api;

import com.nezam.proxy.sy.domains.User;
import com.nezam.proxy.sy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id){
        return service.findById(id);
    }
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User obj){
        return service.create(obj);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@Valid @RequestBody User obj,@PathVariable(value = "id") Long id){
        return service.update(obj,id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){
        return service.delete(id);
    }


}
