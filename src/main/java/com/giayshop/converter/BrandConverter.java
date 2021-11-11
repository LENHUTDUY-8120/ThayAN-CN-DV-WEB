package com.giayshop.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.giayshop.entity.Brand;
import com.giayshop.payload.response.BrandResponse;

public class BrandConverter {

	public static BrandResponse toRs(Brand brand) {
		BrandResponse brandResponse = new BrandResponse();
		brandResponse.setName(brand.getName());
		brandResponse.setCode(brand.getCode());
		brandResponse.setTotalProduct(brand.getProducts().size());
		return brandResponse;
	}
	
	public static List<BrandResponse> toListRs(List<Brand> listBrand){
		return listBrand.stream().map(brand -> toRs(brand)).collect(Collectors.toList());
	}
}
