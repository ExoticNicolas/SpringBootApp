package com.educandoweb.SBApplication.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.SBApplication.entities.Order;
import com.educandoweb.SBApplication.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {

	@Autowired
	OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> orders = orderService.findAll();
		return ResponseEntity.ok().body(orders);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order order = orderService.findById(id);
		return ResponseEntity.ok().body(order);
	}
}
