package com.educandoweb.SBApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.SBApplication.entities.User;
import com.educandoweb.SBApplication.repositories.UserRepository;
import com.educandoweb.SBApplication.services.exceptions.DatabaseException;
import com.educandoweb.SBApplication.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException(id));

	}

	public User insert(User user) {
		return userRepository.save(user);
	}

	public void delete(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ResourceNotFoundException(id);
		}
		try {
			userRepository.deleteById(user.get().getId());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public User update(Long id, User user) {
		try {
			User entity = userRepository.getReferenceById(id);
			updateData(entity, user);
			return userRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	public void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}

}
