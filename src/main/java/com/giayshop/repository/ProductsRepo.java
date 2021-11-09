package com.giayshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.Products;

public interface ProductsRepo extends JpaRepository<Products, Long>{

	public Page<Products> findByTitleContaining(String title, Pageable pageable);
}
