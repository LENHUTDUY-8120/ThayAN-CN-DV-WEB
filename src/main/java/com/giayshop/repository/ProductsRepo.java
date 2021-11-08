package com.giayshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.Products;

public interface ProductsRepo extends JpaRepository<Products, Long>{

}
