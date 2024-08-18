package com.educandoweb.SBApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.SBApplication.entities.Category;
import com.educandoweb.SBApplication.entities.Product;
import com.educandoweb.SBApplication.repositories.ProductRepository;
import com.educandoweb.SBApplication.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;


	public List<Product> findAll() {
		List<Product> products = productRepository.findAll();
		return products;
	}
	

	public Product findById(Long id) {
		Optional<Product> products = productRepository.findById(id);
		return products.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Product insert(Product product) {
		return productRepository.save(product);
	}

	public Product insertCategory(Long id, Long CategoryId) {
		Product p = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		Category cat = categoryService.findById(CategoryId);
		p.getCategories().add(cat);
		return productRepository.save(p);
	}
	
	public Product update(Long id, Product product) {
		try {
			Product p = productRepository.getReferenceById(id);
			updateData(p, product);
			return productRepository.save(p);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}
	
	public void delete(Long id) {
		Product p = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		productRepository.delete(p);
	}
	
	public void updateData(Product entity, Product product) {
		entity.setName(product.getName());
		entity.setDescription(product.getDescription());
		entity.setPrice(product.getPrice());
		entity.setImgUrl(product.getImgUrl());
		
	}
}

