package com.educandoweb.SBApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.SBApplication.entities.Category;
import com.educandoweb.SBApplication.repositories.CategoryRepository;
import com.educandoweb.SBApplication.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;


	public List<Category> findAll() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}
	
	
	public Category findById(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return category;
	}
	
	public Category findByName(String name) {
		try {
		Category cat = categoryRepository.findByName(name);
		return  cat;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	public Category insert(Category category) {
		return categoryRepository.save(category);
	}
	
	public void delete(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		categoryRepository.deleteById(category.getId());
	}
}
