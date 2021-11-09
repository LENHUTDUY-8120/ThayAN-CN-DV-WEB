package com.giayshop.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.giayshop.entity.Products;
import com.giayshop.payload.response.ProductsAdminResponse;

public class ProductConverter {

	public static ProductsAdminResponse toAdminResponse(Products products) {
		ProductsAdminResponse productad = new ProductsAdminResponse();
		productad.setId(products.getId());
		productad.setTitle(products.getTitle());
		productad.setProductCode(products.getProductCode());
		productad.setPrice(products.getPrice());
		productad.setQuantity(products.getQuantity());
		productad.setDescribes(products.getDescribes());
		productad.setBrandName(products.getBrand().getName());
		productad.setChatLieuName(products.getChatLieu().getName());
		productad.setGioiTinh(products.getGioiTinh());
		productad.setColorName(products.getColor().getName());
		productad.setImages(products.getListImage().get(0).getFileName());
		productad.setCreatedDate(products.getCreatedDate());
		productad.setModifyDate(products.getModifiedDate());
		return productad;
	}

	public static List<ProductsAdminResponse> toListAdminResponse(List<Products> products){
		return products.stream().map(product -> toAdminResponse(product))
				.collect(Collectors.toList());
	}
}
