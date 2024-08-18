package com.educandoweb.SBApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.SBApplication.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
