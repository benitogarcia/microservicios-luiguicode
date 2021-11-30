package com.tutorial.codeuserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.codeuserservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
