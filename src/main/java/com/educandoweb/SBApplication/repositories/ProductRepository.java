package com.educandoweb.SBApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.SBApplication.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
