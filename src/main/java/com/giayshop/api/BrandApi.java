package com.giayshop.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giayshop.payload.request.BrandResquest;
import com.giayshop.payload.response.BrandResponse;
import com.giayshop.service.BrandService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/brand")
public class BrandApi {

	@Autowired
	private BrandService brandService;
	
	@GetMapping
	public List<BrandResponse> getAllBrand() {
		return brandService.getAllBrand();
	}
	
	@PostMapping
	public BrandResponse addNewBrand(@RequestBody BrandResquest brandResquest) {
		return brandService.saveBrand(brandResquest);
	}
}
