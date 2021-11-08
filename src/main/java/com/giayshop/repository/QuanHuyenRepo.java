package com.giayshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.QuanHuyen;

public interface QuanHuyenRepo extends JpaRepository<QuanHuyen, String>{

	List<QuanHuyen> findByMatp(String matp);
}
