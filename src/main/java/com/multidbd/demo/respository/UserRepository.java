package com.multidbd.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multidbd.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> { }

