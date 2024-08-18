package com.educandoweb.SBApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.SBApplication.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
