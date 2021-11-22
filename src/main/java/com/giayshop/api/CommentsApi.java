package com.giayshop.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giayshop.payload.request.CommentResquest;
import com.giayshop.payload.response.CommentResponse;
import com.giayshop.service.CommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/comments")
public class CommentsApi {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/{productId}")
	public CommentResponse createComment(@RequestBody CommentResquest cmt,
			@PathVariable Long productId) {
		return commentService.addNewCmt(cmt,productId);
	}
	
	@GetMapping("/{productId}")
	public List<CommentResponse> getListCmt(@PathVariable Long productId) {
		return commentService.listComment(productId);
	}
}
