package com.nezam.proxy.sy.preLoading;

import com.nezam.proxy.sy.domains.User;
import com.nezam.proxy.sy.repository.UserRepository;
import com.nezam.proxy.sy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;
    @Autowired // <3>
    public DatabaseLoader() {
      //  this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception { // <4>
       // this.repository.save(new Employee("Frodo", "Baggins", "ring bearer"));
        userRepository.save(new User(1l,"Ali","password","F1","L1"));
        userRepository.save(new User(2l,"Mohamad","password","F2","L2"));
        userRepository.save(new User(3l,"Walaa","password","F3","L3"));
        userRepository.save(new User(4l,"Reem","password","F4","L4"));
    }
}
