package com.giayshop.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giayshop.payload.request.ChatLieuRequest;
import com.giayshop.payload.response.ChatLieuResponse;
import com.giayshop.service.ChatLieuService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/chatlieu")
public class ChatLieuApi {

	@Autowired
	private ChatLieuService chatLieuService;
	
	@GetMapping
	public List<ChatLieuResponse> getAllChatLieu() {
		return chatLieuService.getAllChatLieu();
	}
	
	@PostMapping
	public ChatLieuResponse addNewChatLieu(@RequestBody ChatLieuRequest chatLieuRequest) {
		return chatLieuService.saveChatLieu(chatLieuRequest);
	}
}
