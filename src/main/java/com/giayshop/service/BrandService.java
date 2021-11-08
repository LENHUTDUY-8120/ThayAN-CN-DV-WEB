package com.giayshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giayshop.config.MapperUtil;
import com.giayshop.entity.Brand;
import com.giayshop.payload.request.BrandResquest;
import com.giayshop.payload.response.BrandResponse;
import com.giayshop.repository.BrandRepo;

@Service
public class BrandService {

	@Autowired
	private BrandRepo brandRepo;
	
	public List<BrandResponse> getAllBrand() {
		return MapperUtil.mapAll(brandRepo.findAll(), BrandResponse.class);
	}
	
	public BrandResponse saveBrand(BrandResquest brandResquest) {
		Brand brand = new Brand();
		brand.setName(brandResquest.getName());
		brand.setCode(brandResquest.getCode());
		return MapperUtil.map(brandRepo.save(brand), BrandResponse.class);
	}
	
}
