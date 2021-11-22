package com.giayshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giayshop.payload.response.AdminAnalysisResponse;
import com.giayshop.repository.BrandRepo;
import com.giayshop.repository.OrdersRepo;
import com.giayshop.repository.ProductsRepo;

@Service
public class AnalysisService {

	@Autowired
	private BrandRepo brandRepo;
	@Autowired
	private ProductsRepo productsRepo;
	@Autowired
	private OrdersRepo ordersRepo;
	
	public AdminAnalysisResponse getAllInfo() {
		AdminAnalysisResponse adrs = new AdminAnalysisResponse();
		adrs.setTotalBrand(brandRepo.count());
		adrs.setTotalProduct(productsRepo.countByStatus("active"));
		adrs.setTotalOrder(ordersRepo.count());
		return adrs;
	}
}
