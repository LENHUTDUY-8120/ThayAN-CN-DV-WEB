package com.giayshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.Brand;

public interface BrandRepo extends JpaRepository<Brand, Long>{

	public Brand findOneByCode(String code);
	
}
