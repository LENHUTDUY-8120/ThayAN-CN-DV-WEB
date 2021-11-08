package com.giayshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.Color;

public interface ColorRepo extends JpaRepository<Color, Long>{

	public Color findOneByCode(String code);
}
