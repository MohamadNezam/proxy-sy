package com.nezam.proxy.sy.repository;

import com.nezam.proxy.sy.models.Proxy;
import com.nezam.proxy.sy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProxyRepository extends JpaRepository<Proxy,Long> {

}
