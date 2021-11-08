package com.giayshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giayshop.config.MapperUtil;
import com.giayshop.entity.Color;
import com.giayshop.payload.request.ColorRequest;
import com.giayshop.payload.response.ColorResponse;
import com.giayshop.repository.ColorRepo;

@Service
public class ColorService {

	@Autowired
	private ColorRepo colorRepo;
	
	public List<ColorResponse> getAllColor() {
		return MapperUtil.mapAll(colorRepo.findAll(), ColorResponse.class);
	}
	
	public ColorResponse saveColor(ColorRequest colorRequest) {
		Color color = new Color();
		color.setName(colorRequest.getName());
		color.setCode(colorRequest.getCode());
		return MapperUtil.map(colorRepo.save(color), ColorResponse.class);
	}
}
