package com.giayshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.Products;

public interface ProductsRepo extends JpaRepository<Products, Long>{

	public Page<Products> findByTitleContainingAndStatus(String title, Pageable pageable,String stt);
	
	public Page<Products> findByStatus(String status,Pageable pageable);
	
	public Products findByIdAndStatus(Long id, String stt);
	
	public Long countByStatus(String stt);
}
