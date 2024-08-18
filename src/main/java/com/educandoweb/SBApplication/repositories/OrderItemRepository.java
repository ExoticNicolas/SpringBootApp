package com.educandoweb.SBApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.SBApplication.entities.OrderItem;
import com.educandoweb.SBApplication.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>
{

}
