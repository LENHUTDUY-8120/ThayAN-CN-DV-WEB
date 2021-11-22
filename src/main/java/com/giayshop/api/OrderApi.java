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

import com.giayshop.payload.request.OrderRequest;
import com.giayshop.payload.response.OrderDetailResponse;
import com.giayshop.payload.response.OrderItemResponse;
import com.giayshop.payload.response.OrderResponse;
import com.giayshop.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class OrderApi {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/orders")
	public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
		return orderService.createOrder(orderRequest);
	}
	
	@GetMapping("/admin/orders")
	public List<OrderDetailResponse> getListOrderAd(){
		return orderService.getListOrderAd();
	}
	
	@GetMapping("/admin/orders/{id}")
	public OrderItemResponse getOrderItemsAd(@PathVariable Long id){
		return orderService.getOrderItemResponse(id);
	}
}
