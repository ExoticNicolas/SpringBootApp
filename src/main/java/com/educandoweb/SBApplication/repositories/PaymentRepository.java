package com.educandoweb.SBApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.SBApplication.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
