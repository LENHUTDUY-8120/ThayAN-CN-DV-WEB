package com.giayshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.XaPhuongThiTran;

public interface XaPhuongThiTranRepo extends JpaRepository<XaPhuongThiTran, String>{

	List<XaPhuongThiTran> findByMaqh(String maqh);
}
