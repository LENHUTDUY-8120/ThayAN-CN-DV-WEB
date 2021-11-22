package com.giayshop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giayshop.payload.response.AdminAnalysisResponse;
import com.giayshop.service.AnalysisService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/analysis")
public class AnalysisApi {

	@Autowired
	private AnalysisService analysisService;
	
	@GetMapping
	public AdminAnalysisResponse getInfo() {
		return analysisService.getAllInfo();
	}
}
