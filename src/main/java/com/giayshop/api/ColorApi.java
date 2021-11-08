package com.giayshop.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giayshop.payload.request.ColorRequest;
import com.giayshop.payload.response.ColorResponse;
import com.giayshop.service.ColorService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/color")
public class ColorApi {

	@Autowired
	private ColorService colorService;
	
	@GetMapping
	public List<ColorResponse> getAllColor() {
		return colorService.getAllColor();
	}
	
	@PostMapping
	public ColorResponse addNewColor(@RequestBody ColorRequest colorRequest) {
		return colorService.saveColor(colorRequest);
	}
}
