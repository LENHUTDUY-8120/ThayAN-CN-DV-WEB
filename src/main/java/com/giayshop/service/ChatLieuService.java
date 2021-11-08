package com.giayshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giayshop.config.MapperUtil;
import com.giayshop.entity.ChatLieu;
import com.giayshop.payload.request.ChatLieuRequest;
import com.giayshop.payload.response.ChatLieuResponse;
import com.giayshop.repository.ChatLieuRepo;

@Service
public class ChatLieuService {

	@Autowired
	private ChatLieuRepo chatLieuRepo;
	
	public List<ChatLieuResponse> getAllChatLieu() {
		return MapperUtil.mapAll(chatLieuRepo.findAll(), ChatLieuResponse.class);
	}
	
	public ChatLieuResponse saveChatLieu(ChatLieuRequest chatLieuRequest) {
		ChatLieu chatLieu = new ChatLieu();
		chatLieu.setCode(chatLieuRequest.getCode());
		chatLieu.setName(chatLieuRequest.getName());
		return MapperUtil.map(chatLieuRepo.save(chatLieu), ChatLieuResponse.class);
	}
}
