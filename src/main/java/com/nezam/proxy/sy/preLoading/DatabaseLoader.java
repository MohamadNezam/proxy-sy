package com.nezam.proxy.sy.preLoading;

import com.nezam.proxy.sy.controllers.UserController;
import com.nezam.proxy.sy.models.Role;
import com.nezam.proxy.sy.models.User;
import com.nezam.proxy.sy.repository.RoleRepository;
import com.nezam.proxy.sy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired // <3>
    public DatabaseLoader() {
      //  this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {

        // *********************************
        // Add Roles
          Role r1 = new Role("ADMIN");
          Role r2 = new Role("DELETE");
          roleRepository.saveAll(Arrays.asList(r1,r2));

        // *********************************
        // Add Users

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(11);
        String password = bCryptPasswordEncoder.encode("password");

        User u1 = new User("Ali",password,"F1","L1",true);
        User u2 = new User("Mohamad",password,"F2","L2",true);
        User u3 = new User("Walaa",password,"F3","L3",true);
        User u4 = new User("Reem",password,"F4","L4",true);
        userRepository.saveAll(Arrays.asList(u1,u2,u3,u4));

        // *********************************
        // Add User Roles
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();

        adminRoles.add(r1);
       // adminRoles.add(r2);
        u1.setRoles(adminRoles);
        userRepository.save(u1);
        adminRoles.add(r2);
        u2.setRoles(adminRoles);

        userRepository.save(u2);
      //  logger.info(u1.getId().toString());
      //  logger.info(u1.getRoles().toString());



    }
}
