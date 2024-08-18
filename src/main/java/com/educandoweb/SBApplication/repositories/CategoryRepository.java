package com.educandoweb.SBApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.SBApplication.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	public Category findByName(String name);
}
