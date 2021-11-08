package com.giayshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Long>{

}
