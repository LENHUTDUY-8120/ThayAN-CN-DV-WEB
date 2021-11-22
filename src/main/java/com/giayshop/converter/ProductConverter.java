package com.giayshop.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.giayshop.entity.Comments;
import com.giayshop.entity.Products;
import com.giayshop.payload.response.ProductsAdminResponse;
import com.giayshop.payload.response.ProductsCusResponse;

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
		productad.setViews(products.getViews());
		return productad;
	}

	public static List<ProductsAdminResponse> toListAdminResponse(List<Products> products) {
		return products.stream().map(product -> toAdminResponse(product)).collect(Collectors.toList());
	}

	public static ProductsCusResponse toCusResponse(Products products) {
		ProductsCusResponse product = new ProductsCusResponse();
		product.setId(products.getId());
		product.setTitle(products.getTitle());
		product.setProductCode(products.getProductCode());
		product.setPrice(products.getPrice());
		product.setQuantity(products.getQuantity());
		product.setDescribes(products.getDescribes());
		product.setBrandName(products.getBrand().getName());
		product.setChatLieuName(products.getChatLieu().getName());
		product.setGioiTinh(products.getGioiTinh());
		product.setColorName(products.getColor().getName());
		List<String> listImage = products.getListImage().stream().map(image -> image.getFileName())
				.collect(Collectors.toList());
		product.setImages(listImage);
		product.setCreatedDate(products.getCreatedDate());
		product.setModifyDate(products.getModifiedDate());
		product.setViews(products.getViews());
		List<Comments> listCmt = products.getComments();
		if (listCmt.isEmpty()) {
			product.setRate(0);
		} else {
			int sum = 0;
			for (Comments comment : listCmt) {
				sum += comment.getRate();
			}
			product.setRate((int) Math.round(sum / listCmt.size()));
		}
		return product;
	};

	public static List<ProductsCusResponse> toListCusResponse(List<Products> products) {
		return products.stream().map(product -> toCusResponse(product)).collect(Collectors.toList());
	}
}
