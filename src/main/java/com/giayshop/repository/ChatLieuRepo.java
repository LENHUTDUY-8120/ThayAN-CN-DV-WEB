package com.giayshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.ChatLieu;

public interface ChatLieuRepo extends JpaRepository<ChatLieu, Long>{

	public ChatLieu findOneByCode(String code);
}
