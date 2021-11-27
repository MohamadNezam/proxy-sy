package com.nezam.proxy.sy.repository;

import com.nezam.proxy.sy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByUsernameContainsIgnoreCase(String username);
    User findByUsername(String username);
}
