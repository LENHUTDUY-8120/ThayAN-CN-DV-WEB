package com.giayshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long>{

}
