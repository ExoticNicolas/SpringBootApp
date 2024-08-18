package com.educandoweb.SBApplication.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.SBApplication.entities.Category;
import com.educandoweb.SBApplication.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> categories = categoryService.findAll();
		return ResponseEntity.ok().body(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category category = categoryService.findById(id);
		return ResponseEntity.ok().body(category);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Category> findByName(@PathVariable String name) {
		Category cat = categoryService.findByName(name);
		return ResponseEntity.ok().body(cat);
	}
	
	@PostMapping
	public ResponseEntity<Category> insert(@RequestBody Category category){
		categoryService.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).body(category);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		categoryService.delete(id);
		return ResponseEntity.ok().build();
	}
}
