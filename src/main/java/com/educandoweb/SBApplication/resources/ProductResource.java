package com.educandoweb.SBApplication.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.SBApplication.entities.Product;
import com.educandoweb.SBApplication.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> products = productService.findAll();
		return ResponseEntity.ok().body(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product product = productService.findById(id);
		return ResponseEntity.ok().body(product);
	}
	
	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product product){
		productService.insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}
	
	@PutMapping
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product){
		product = productService.update(id, product);
		return ResponseEntity.ok().body(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> insertCategory(@PathVariable Long id, @RequestParam Long CategoryId){
		productService.insertCategory(id, CategoryId);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		productService.delete(id);
		return ResponseEntity.ok().build();
	}
}
